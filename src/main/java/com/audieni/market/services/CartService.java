package com.audieni.market.services;

import com.audieni.market.models.Cart;
import com.audieni.market.repositories.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Cart service to handle requests between controllers and repositories
 */
@Service
@Transactional
public class CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    /**
     * Find products in cart by user id
     * @param userId User ID
     * @return Optional of list of products in cart
     */
    public Optional<List<Cart>> findByUserId(int userId) {
        return cartRepository.findByUserId(userId);
    }

    /**
     * Find products in cart by user and product id
     * @param userId User ID
     * @param productId Product ID
     * @return Optional of product in cart
     */
    public Optional<Cart> findByUserIdAndProductId(int userId, int productId) {
        return cartRepository.findByUserIdAndProductId(userId, productId);
    }

    /**
     * Save new data to cart table
     * @param cart Cart object
     * @return Cart JSON
     */
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    /**
     * Delete data from cart table
     * @param cart Cart object
     */
    public void delete(Cart cart) {
        cartRepository.delete(cart);
    }
}