package com.audieni.market.dtos;

import com.audieni.market.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductDto {
    private Product product;
    private int stock;
}
