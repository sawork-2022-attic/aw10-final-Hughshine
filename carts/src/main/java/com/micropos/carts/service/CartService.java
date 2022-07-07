package com.micropos.carts.service;

import com.micropos.carts.model.Cart;
import com.micropos.carts.model.Item;
import com.micropos.carts.model.Order;

import reactor.core.publisher.Mono;

public interface CartService {
    public Mono<Cart> addItem(String cartId, Item item);
    public Mono<Order> checkout(String cartId);
}
