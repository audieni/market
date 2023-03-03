package com.audieni.market.services;

import com.audieni.market.models.Cart;
import com.audieni.market.repositories.CartRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CartServiceTests {
    public CartService sut;

    @Mock
    private Cart mockCart;

    @Mock
    private List<Cart> mockCartList;

    @Mock
    CartRepository mockCartRepository;

    private final int userId = 1;
    private final int productId = 1;

    @Test
    void findByUserIdTest() {
        sut = new CartService(mockCartRepository);
        Mockito.when(mockCartRepository.findByUserId(userId)).thenReturn(Optional.of(mockCartList));
        Optional<List<Cart>> cart = sut.findByUserId(userId);
        Assertions.assertEquals(Optional.of(mockCartList), cart);
    }

    @Test
    void findByUserIdAndProductIdTest() {
        sut = new CartService(mockCartRepository);
        Mockito.when(mockCartRepository.findByUserIdAndProductId(userId, productId))
                .thenReturn(Optional.of(mockCart));
        Optional<Cart> cart = sut.findByUserIdAndProductId(userId, productId);
        Assertions.assertEquals(Optional.of(mockCart), cart);
    }

    @Test
    void saveTest() {
        sut = new CartService(mockCartRepository);
        Mockito.when(mockCartRepository.save(mockCart)).thenReturn(mockCart);
        Cart cart = sut.save(mockCart);
        Assertions.assertEquals(mockCart, cart);
    }

    @Test
    void deleteTest() {
        sut = new CartService(mockCartRepository);
        sut.delete(mockCart);
        Mockito.verify(mockCartRepository).delete(mockCart);
    }
}
