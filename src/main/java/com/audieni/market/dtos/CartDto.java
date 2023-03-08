package com.audieni.market.dtos;

import com.audieni.market.models.Product;
import com.audieni.market.models.User;
import com.audieni.market.services.ProductService;
import com.audieni.market.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private int userId;
    private int productId;
    private int stock;
    private UserService userService;
    private ProductService productService;

    public CartDto(int userId, int productId, int stock) {
        this.userId = userId;
        this.productId = productId;
        this.stock = stock;
    }

    public User getUser() {
        return userService.findById(this.userId);
    }

    public Product getProduct() {
        return productService.findById(this.productId);
    }
}
