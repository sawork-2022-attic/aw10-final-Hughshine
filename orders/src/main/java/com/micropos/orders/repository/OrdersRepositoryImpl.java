package com.micropos.orders.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.micropos.orders.model.Cart;
import com.micropos.orders.model.Order;

import reactor.core.publisher.Mono;

@Repository
public class OrdersRepositoryImpl implements OrdersRepository{

    @Autowired
    private OrdersDb db;

    @Override
    public Mono<Order> get(String id) {
        return db.findById(id).switchIfEmpty(Mono.empty());
    }
    
    @Override
    public Mono<Order> save(Cart cart) {
        return db.save(Order.sampleOrder(cart));
    }
}
