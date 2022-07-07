package com.micropos.delivery.controller;

import org.springframework.web.bind.annotation.RestController;

import com.micropos.delivery.repository.DeliveryDb;
import com.micropos.delivery.repository.DeliveryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class DeliveryController {

    @Autowired
    DeliveryDb db;

    @Autowired
    DeliveryRepository deliverRepository;
    
    @GetMapping("/all")
    public Flux<String> all() {
        return deliverRepository.all();
        // return db.findAll().map(Delivery::getId);
    }
}
