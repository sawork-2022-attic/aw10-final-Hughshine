package com.micropos.orders.service;

import com.micropos.orders.model.Cart;
import com.micropos.orders.model.Order;

import reactor.core.publisher.Mono;

public interface OrderService {
    public Mono<Order> createOrder(Cart cart);
}
