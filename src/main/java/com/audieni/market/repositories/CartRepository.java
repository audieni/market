package com.audieni.market.repositories;

import com.audieni.market.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query(value = "SELECT * FROM carts WHERE user_id = :userId", nativeQuery = true)
    Optional<List<Cart>> findByUserId(int userId);

    @Query(value = "SELECT * FROM carts WHERE user_id = :userId AND product_id = :productId", nativeQuery = true)
    Optional<Cart> findByUserIdAndProductId(int userId, int productId);
}
