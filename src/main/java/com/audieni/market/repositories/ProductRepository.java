package com.audieni.market.repositories;

import com.audieni.market.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for working with products table
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    /**
     * Find specific product by ID
     * @param id Product ID
     * @return Product based on its ID
     */
    Product findById(int id);
}
