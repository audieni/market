package com.audieni.market.services;

import com.audieni.market.models.Order;
import com.audieni.market.repositories.OrderProductRepository;
import com.audieni.market.repositories.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    public OrderService sut;

    @Mock
    private Order mockOrder;

    @Mock
    private List<Order> mockOrderList;

    @Mock
    OrderRepository mockOrderRepository;

    @Mock
    OrderProductRepository mockOrderProductRepository;

    @Test
    void findByUserIdTest() {
        sut = new OrderService(mockOrderRepository, mockOrderProductRepository);
        int userId = 1;
        Mockito.when(mockOrderRepository.findByUserId(userId)).thenReturn(Optional.of(mockOrderList));
        Optional<List<Order>> orders = sut.findByUserId(userId);
        Assertions.assertEquals(Optional.of(mockOrderList), orders);
    }

    @Test
    void saveTest() {
        sut = new OrderService(mockOrderRepository, mockOrderProductRepository);
        Mockito.when(mockOrderRepository.save(mockOrder)).thenReturn(mockOrder);
        Optional<Order> order = Optional.of(sut.save(mockOrder));
        Assertions.assertEquals(Optional.of(mockOrder), order);
    }
}
