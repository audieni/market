package com.audieni.market.services;

import com.audieni.market.models.Product;
import com.audieni.market.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Product service to handle requests between controllers and repositories
 */
@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Find all products in database
     * @return List of all products
     */
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * Find a product by ID
     * @param id Product ID
     * @return Product by ID
     */
    public Product findById(int id) {
        return productRepository.findById(id);
    }

    /**
     * Save a new product or update a product
     * @param product Product object
     * @return Newly created/updated product
     */
    public Product save(Product product) {
        return productRepository.save(product);
    }

    /**
     * Save a list of products at once
     * @param products List of products
     * @return List of newly created/saved products
     */
    public List<Product> saveAll(List<Product> products) {
        return productRepository.saveAll(products);
    }
}
