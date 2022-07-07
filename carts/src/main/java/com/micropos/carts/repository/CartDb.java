package com.micropos.carts.repository;

import com.micropos.carts.model.Cart;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CartDb extends ReactiveMongoRepository<Cart, String> {
}
