package com.micropos.carts.repository;

import com.micropos.carts.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public class CartRepositoryImpl implements CartRepository {

    @Autowired
    private CartDb db;

    @Override
    public Mono<Cart> get(String id) {
        return db.findById(id).switchIfEmpty(db.save(new Cart().withId(id)));
    }

    @Override
    public Mono<Cart> update(Cart cart) {
        return Mono.just(cart).filterWhen(c -> db.existsById(c.getId())).flatMap(c -> db.save(c));
    }

    @Override
    public Mono<Cart> remove(String id) {
        return db.existsById(id).flatMap(has -> {
            if (has) {
                return get(id).flatMap(c -> db.deleteById(id).thenReturn(c));
            }
            return Mono.empty();
        });
    }
}
