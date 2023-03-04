package com.audieni.market.controllers;

import com.audieni.market.annotations.Authorized;
import com.audieni.market.models.Product;
import com.audieni.market.services.ProductService;
import com.audieni.market.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Authorized
    @GetMapping
    public ResponseEntity<List<Product>> findAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }
}
