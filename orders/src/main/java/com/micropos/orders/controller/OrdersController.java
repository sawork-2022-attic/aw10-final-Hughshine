package com.micropos.orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micropos.orders.model.Cart;
import com.micropos.orders.model.Order;
import com.micropos.orders.repository.OrdersRepository;
import com.micropos.orders.service.OrderService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class OrdersController {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    OrderService orderService;

    @PostMapping("/order")
    public Mono<Order> createOrder(@RequestBody Cart cart)  {
        // System.out.println("Hi there! I'm creating an order!");
        // return Mono.just(Order.sampleOrder(cart));
        return orderService.createOrder(cart);
    }

    @GetMapping("/order/{id}")
    public Mono<Order> getOrder(@PathVariable String id) {
        return ordersRepository.get(id);
    }
}
