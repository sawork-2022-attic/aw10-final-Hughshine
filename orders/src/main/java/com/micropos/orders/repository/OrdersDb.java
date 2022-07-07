package com.micropos.orders.repository;

import com.micropos.orders.model.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OrdersDb extends ReactiveMongoRepository<Order, String> {
}
