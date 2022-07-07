package com.micropos.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.micropos.orders.model.Cart;
import com.micropos.orders.model.Order;
import com.micropos.orders.repository.OrdersRepository;

import reactor.core.publisher.Mono;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    private StreamBridge streamBridge;

    @Override
    public Mono<Order> createOrder(Cart cart) {
        return ordersRepository.save(cart).flatMap(order -> {
            streamBridge.send("deliver", order);
            return Mono.just(order);
        });
    }
}
