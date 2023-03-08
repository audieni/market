package com.audieni.market.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column
    private String name;

    @Column
    private double price;

    @Column
    private int stock;

    @Column
    private String image;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference(value = "carts_products")
    List<Cart> carts;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference(value = "orders_products")
    List<OrderProduct> orders;

    public Product(int productId, int stock) {
        this.productId = productId;
        this.stock = stock;
    }

    public Product(int productId, String name, int stock) {
        this.productId = productId;
        this.name = name;
        this.stock = stock;
    }
}
