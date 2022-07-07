package com.micropos.products.repository;

import com.micropos.products.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductDbPlain extends MongoRepository<Product, String>{
    
}
