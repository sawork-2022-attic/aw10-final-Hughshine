package com.micropos.carts.service;

// import com.micropos.carts.exception.OrderGenerationFailedException;
import com.micropos.carts.exception.ProductNotFoundException;
import com.micropos.carts.model.Cart;
import com.micropos.carts.model.Item;
import com.micropos.carts.model.Order;
import com.micropos.carts.model.Product;
import com.micropos.carts.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Override
    public Mono<Cart> addItem(String cartId, Item item) {
        return cartRepository.get(cartId).flatMap(cart -> {
            var items = cart.getItems();
            var target = items.stream().filter(i -> i.getProductId().equals(item.getProductId())).findFirst();
            if (target.isPresent()) {
                var targetItem = target.get();
                var quan = targetItem.getQuantity() + item.getQuantity();
                if (quan > 0) {
                    target.get().setQuantity(quan);
                } else {
                    items.remove(targetItem);
                    cart.setItems(items);
                }
            } else {
                if (item.getQuantity() > 0) {
                    try {
                        // Check if product exists
                        Product product = productService.get(item.getProductId());
                        if (product != null) {
                            items.add(item.withProductId(product.getId()));
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                        return Mono.error(new ProductNotFoundException());
                    }
                }
            }
            return cartRepository.update(cart);
        });
    }

    @Override
    public Mono<Order> checkout(String cartId) {
        return cartRepository.get(cartId).flatMap(cart -> {
            try {
                Order order = orderService.createOrder(cart);
                if (order != null) {
                    return cartRepository.remove(cartId).thenReturn(order);
                }
            } catch (Exception e) {
                System.out.println(e);
                // return Mono.error(new OrderGenerationFailedException());
            }
            return cartRepository.remove(cartId).thenReturn(Order.sampleOrder(cart));
        });
    }
}
