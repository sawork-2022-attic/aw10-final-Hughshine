package com.micropos.delivery;

import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.micropos.delivery.model.Order;
import com.micropos.delivery.repository.DeliveryRepository;


public class DeliveryMaker implements Consumer<Order> {
    public static final Logger log = LoggerFactory.getLogger(DeliveryMaker.class);

    @Autowired
    private DeliveryRepository deliverRepository;

    @Override
    public void accept(Order order) {
        log.info("Make delivery for order {}", order.getId());
        deliverRepository
            .save(order)
            .doOnNext((d) -> log.info(String.format("Order delivered: %s", d.getId())))
            .block();
    }
    
}
