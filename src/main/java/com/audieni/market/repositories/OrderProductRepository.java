package com.audieni.market.repositories;

import com.audieni.market.models.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {
    @Query(value = "SELECT * FROM orders_products WHERE order_id = :orderId", nativeQuery = true)
    Optional<List<OrderProduct>> findByOrderId(int orderId);
}
