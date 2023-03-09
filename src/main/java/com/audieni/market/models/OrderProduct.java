package com.audieni.market.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders_products")
public class OrderProduct {
    @Id
    @Column(name = "order_product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderProductId;

    @Column
    private int stock;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    @JsonBackReference(value = "orders_orders_products")
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @JsonBackReference(value = "orders_products")
    private Product product;

    public OrderProduct(Order order, Product product, int stock) {
        this.order = order;
        this.product = product;
        this.stock = stock;
    }

    public int getProductId() {
        return product.getProductId();
    }

    public String getProductName() {
        return product.getName();
    }

    public String getProductImage() {
        return product.getImage();
    }

    public double getProductPrice() {
        return product.getPrice();
    }
}
