package com.audieni.market.controllers;

import com.audieni.market.annotations.Authorized;
import com.audieni.market.models.Product;
import com.audieni.market.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Product Controller to handle HTTP requests
 */
@RestController
@RequestMapping("/products")
@CrossOrigin(origins = {"http://localhost:4200", "http://129.80.51.149"}, allowCredentials = "true")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Find a list of all products in database
     * @return Response with list of all products in database
     */
    @Authorized
    @GetMapping
    public ResponseEntity<List<Product>> findAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }
}
