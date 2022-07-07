package com.micropos.products.controller;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micropos.products.batch.Jobs;
import com.micropos.products.model.Product;
import com.micropos.products.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ProductsController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Jobs jobs;

    String filename = "meta_Magazine_Subscriptions_100";

    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public Mono<Product> get(@PathVariable String id)  {
        return productService.get(id).switchIfEmpty(Mono.just(Product.sampleProduct(id)));
        // return Mono.just(Product.sampleProduct(id));
    }

    @GetMapping("/import")
    public Mono<String> importProducts() {
        try {
            jobLauncher.run(jobs.importProducts(jobs.defaultFile()), new JobParameters());
        } catch (Exception e) {
            return Mono.error(e);
        }
        return Mono.just("OK");
    }

    @GetMapping("/all")
    public Flux<Product> all()  {
        return productService.all();
    }
}
