package com.micropos.carts.model;

import java.util.ArrayList;
import java.util.List;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class Order {
    private String id;

    private List<Item> items = new ArrayList<>();

    static public Order sampleOrder(Cart cart) {
        return new Order().withId(cart.getId() + "-" + Instant.now()).withItems(cart.getItems());
    }
}
