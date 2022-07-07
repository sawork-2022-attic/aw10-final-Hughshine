package com.micropos.products.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micropos.products.model.Product;
import com.micropos.products.repository.ProductDb;
import com.micropos.products.repository.ProductDbPlain;
import com.micropos.products.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDb db;

    @Autowired
    ProductDbPlain dbPlain;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Mono<Product> get(String id) {
        return db.findById(id);
    }

    @Override
    public Flux<Product> all() {
        return db.findAll().take(10);
    }    
}
