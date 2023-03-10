package com.audieni.market.repositories;

import com.audieni.market.models.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for working with orders_products table
 */
@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {
    /**
     * Find products by Order ID
     * @param orderId Order's ID
     * @return Optional of list of products by order id
     */
    @Query(value = "SELECT * FROM orders_products WHERE order_id = :orderId", nativeQuery = true)
    Optional<List<OrderProduct>> findByOrderId(int orderId);
}
