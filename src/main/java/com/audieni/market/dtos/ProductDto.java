package com.audieni.market.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private int productId;
    private int userId;
    private double price;
    private int stock;

    public ProductDto(int productId, int stock) {
        this.productId = productId;
        this.stock = stock;
    }
}
