package com.example.demo.order;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Cacheable(value = "orders")
    public Order get(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("order not found"));

    }

    @CachePut(value = "orders", key = "#order.id")
    public Order update(Order order) {
        if (orderRepository.existsById(order.getId())) {
            return orderRepository.save(order);
        }
        throw new IllegalArgumentException("Order must have an id");
    }

    @CacheEvict(value = "orders", key = "#id")
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
