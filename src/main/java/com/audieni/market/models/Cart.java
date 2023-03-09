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
@Table(name = "carts")
public class Cart {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @Column
    private int stock;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "carts_users")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @JsonBackReference(value = "carts_products")
    private Product product;

    public Cart(User user, Product product, int stock) {
        this.user = user;
        this.product = product;
        this.stock = stock;
    }

    public int getUserId() {
        return user.getUserId();
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
