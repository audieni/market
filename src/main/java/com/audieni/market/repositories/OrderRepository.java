package com.audieni.market.repositories;

import com.audieni.market.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "SELECT * FROM orders WHERE user_id = :userId", nativeQuery = true)
    Optional<List<Order>> findByUserId(int userId);
}
