package com.micropos.orders.repository;

import com.micropos.orders.model.Cart;
import com.micropos.orders.model.Order;

import reactor.core.publisher.Mono;

public interface OrdersRepository {
    public Mono<Order> get(String id);
    public Mono<Order> save(Cart cart);
}