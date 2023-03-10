package com.audieni.market.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "orders_users")
    @JsonProperty
    private User user;

    @OneToMany(mappedBy = "order")
    @JsonManagedReference(value = "orders_orders_products")
    List<OrderProduct> orderProduct;

    public Order(User user, List<OrderProduct> orderProduct) {
        this.user = user;
        this.orderProduct = orderProduct;
    }
}
