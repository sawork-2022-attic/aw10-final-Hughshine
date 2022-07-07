package com.micropos.carts.controller;

import com.micropos.carts.exception.ProductNotFoundException;
import com.micropos.carts.model.Cart;
import com.micropos.carts.model.Item;
import com.micropos.carts.model.Order;
import com.micropos.carts.repository.CartRepository;
import com.micropos.carts.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class CartsController {
    @Autowired
    private CartRepository repository;

    @Autowired
    private CartService service;

    @GetMapping("/{id}")
    public Mono<Cart> get(@PathVariable String id) {
        return repository.get(id);
    }

    @PostMapping(value = "/{id}")
    public Mono<Cart> update(@PathVariable("id") String id, @RequestBody Item item) throws ProductNotFoundException {
        return service.addItem(id, item);
    }

    @GetMapping("/checkout/{id}")
    public Mono<Order> checkout(@PathVariable String id) {
        return service.checkout(id);
    }
}