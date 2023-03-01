package com.audieni.market.services;

import com.audieni.market.models.Order;
import com.audieni.market.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Optional<List<Order>> findByUserId(int userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
