package com.micropos.carts.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.micropos.carts.model.Cart;
import com.micropos.carts.model.Order;


@FeignClient(name = "orders", path = "/api")
public interface OrderService {
    @PostMapping("/order") 
    public Order createOrder(@RequestBody Cart cart);
}
