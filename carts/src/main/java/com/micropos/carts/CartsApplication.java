package com.micropos.carts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class CartsApplication {

	@Bean
	HttpMessageConverters converters() { 
		return new HttpMessageConverters();
	}
	public static void main(String[] args) {
		SpringApplication.run(CartsApplication.class, args);
	}
}
