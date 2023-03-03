package com.audieni.market.repositories;

import com.audieni.market.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<List<Cart>> findByUserId(int userId);
    Optional<Cart> findByUserIdAndProductId(int userId, int productId);
}
