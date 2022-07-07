package com.micropos.delivery.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;


@Document(collection = "delivery")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@With
public class Delivery {
    private String id;
    private List<Item> items = new ArrayList<>();
    private String time;

    public static Delivery make(Order order) {
        return new Delivery().withId(order.getId()).withItems(order.getItems()).withTime(Instant.now().toString());
    }
}
