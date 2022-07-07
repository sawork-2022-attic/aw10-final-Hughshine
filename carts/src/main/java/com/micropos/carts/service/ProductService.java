package com.micropos.carts.service;

import com.micropos.carts.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;

@FeignClient(name = "products", path = "/api")
// , fallback = ProductServiceFallBack.class)
public interface ProductService {
    @GetMapping("/{id}")
    public Product get(@PathVariable("id") String id);
}
