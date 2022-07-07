package com.micropos.products.service;

import org.springframework.stereotype.Service;

import com.micropos.products.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<Product> get(String id);
    Flux<Product> all();
}
