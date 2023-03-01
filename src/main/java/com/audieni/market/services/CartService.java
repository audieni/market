package com.audieni.market.services;

import com.audieni.market.models.Cart;
import com.audieni.market.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Optional<List<Cart>> findByUserId(int userId) {
        return cartRepository.findByUserId(userId);
    }

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }
}
