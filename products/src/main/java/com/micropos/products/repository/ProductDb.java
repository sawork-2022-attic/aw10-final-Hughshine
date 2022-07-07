package com.micropos.products.repository;

import com.micropos.products.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductDb extends ReactiveMongoRepository<Product, String> {
}
