package com.audieni.market.repositories;

import com.audieni.market.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for working with carts table
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    /**
     * Query database for rows with userId
     * @param userId User's ID
     * @return Optional of list of products in user's cart
     */
    @Query(value = "SELECT * FROM carts WHERE user_id = :userId", nativeQuery = true)
    Optional<List<Cart>> findByUserId(int userId);

    /**
     * Query database for specific product by userId and productId
     * @param userId User's ID
     * @param productId Product's ID
     * @return Optional of product in user's cart
     */
    @Query(value = "SELECT * FROM carts WHERE user_id = :userId AND product_id = :productId", nativeQuery = true)
    Optional<Cart> findByUserIdAndProductId(int userId, int productId);
}
