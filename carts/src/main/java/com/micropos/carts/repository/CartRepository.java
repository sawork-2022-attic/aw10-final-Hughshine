package com.micropos.carts.repository;

import com.micropos.carts.model.Cart;

import reactor.core.publisher.Mono;

public interface CartRepository {
    public Mono<Cart> get(String id);

    public Mono<Cart> update(Cart item);

    public Mono<Cart> remove(String id);
}
