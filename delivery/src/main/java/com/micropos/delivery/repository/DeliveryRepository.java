package com.micropos.delivery.repository;

import com.micropos.delivery.model.Delivery;
import com.micropos.delivery.model.Order;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DeliveryRepository {
    public Mono<Delivery> save(Order order);
    public Flux<String> all();
}
