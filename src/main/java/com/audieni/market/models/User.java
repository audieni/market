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
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String country;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value = "carts_users")
    List<Cart> carts;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value = "orders_users")
    List<Order> orders;

    public User(int userId, String email, String password, String country) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.country = country;
    }

    public User(String email, String password, String country) {
        this.email = email;
        this.password = password;
        this.country = country;
    }
}
