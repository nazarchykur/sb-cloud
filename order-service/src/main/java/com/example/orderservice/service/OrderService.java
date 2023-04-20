package com.example.orderservice.service;


import com.example.orderservice.dto.Product;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@NoArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
//    private ProductClient productClient;
    
    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
//    @Autowired
//    public void setProductClient(ProductClient productClient) {
//        this.productClient = productClient;
//    }

    public Order createOrder(String productId, int quantity) {
        // Get the product from the product-service
//        Product product = productClient.getProduct(productId);

        // Create a new order object
        Order order = new Order();
        order.setProductId(productId);
        order.setQuantity(quantity);

        // Save the order to MongoDB
        order = orderRepository.save(order);

        return order;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    public Order updateOrder(String id, Order updatedOrder) {
        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()) {
            Order existingOrder = order.get();
            existingOrder.setProductId(updatedOrder.getProductId());
            existingOrder.setQuantity(updatedOrder.getQuantity());
            return orderRepository.save(existingOrder);
        } else {
            return null;
        }
    }

    public void deleteOrder(String id) {
        orderRepository.findById(id)
                        .ifPresentOrElse(order -> orderRepository.deleteById(order.getId()), NoSuchElementException::new);
    }
}
