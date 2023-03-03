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

    public ProductController(UserService userService, ProductService productService) {
        this.productService = productService;
    }

    @Authorized
    @GetMapping
    public ResponseEntity<List<Product>> findAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping("/addTestProducts")
    public ResponseEntity<List<Product>> addProduct() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "Product 1", 10));
        productList.add(new Product(2, "Product 2", 20));
        productList.add(new Product(3, "Product 3", 30));
        productList.add(new Product(4, "Product 4", 40));
        productList.add(new Product(5, "Product 5", 50));

        return ResponseEntity.ok(productService.saveAll(productList));
    }
}
