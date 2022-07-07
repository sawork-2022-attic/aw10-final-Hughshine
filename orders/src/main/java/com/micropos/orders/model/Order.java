package com.micropos.orders.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Document(collection = "orders")
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
