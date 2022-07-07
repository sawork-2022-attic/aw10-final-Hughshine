package com.micropos.delivery.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.micropos.delivery.model.Delivery;
import com.micropos.delivery.model.Order;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class DeliveryRepositoryImpl implements DeliveryRepository {

    @Autowired
    DeliveryDb db;

    @Override
    public Mono<Delivery> save(Order order) {
        return db.save(Delivery.make(order));    
    }

    @Override
    public Flux<String> all() {
        return db.findAll().map(delivery -> {
            return delivery.toString();
        });
    }

}
