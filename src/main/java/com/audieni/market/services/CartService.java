package com.audieni.market.services;

import com.audieni.market.models.Cart;
import com.audieni.market.repositories.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Optional<List<Cart>> findByUserId(int userId) {
        return cartRepository.findByUserId(userId);
    }

    public Optional<Cart> findByUserIdAndProductId(int userId, int productId) {
        return cartRepository.findByUserIdAndProductId(userId, productId);
    }

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public void delete(Cart cart) {
        cartRepository.delete(cart);
    }
}