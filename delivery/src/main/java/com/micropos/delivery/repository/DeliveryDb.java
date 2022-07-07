package com.micropos.delivery.repository;
import com.micropos.delivery.model.Delivery;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DeliveryDb extends ReactiveMongoRepository<Delivery, String> {
    
}
