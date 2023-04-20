package com.example.orderservice.client;

import com.example.orderservice.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@Component
//@FeignClient(name = "product-service")
//public interface ProductClient {
//    @GetMapping("/products/{id}")
//    Product getProduct(@PathVariable("id") String id);
//}
