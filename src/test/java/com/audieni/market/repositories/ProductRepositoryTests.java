package com.audieni.market.repositories;

import com.audieni.market.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ProductRepositoryTests {
    @Autowired
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    void setup() {
        product = Product.builder()
                .id(1)
                .name("Product")
                .stock(10)
                .build();
    }

    @Test
    void findByIdTest() {
        productRepository.save(product);
        Product returnedProduct = productRepository.findById(1);
        Assertions.assertNotNull(returnedProduct);
    }
}
