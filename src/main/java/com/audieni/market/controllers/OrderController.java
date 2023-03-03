package com.audieni.market.controllers;

import com.audieni.market.annotations.Authorized;
import com.audieni.market.dtos.OrderDto;
import com.audieni.market.dtos.OrderProductDto;
import com.audieni.market.dtos.UserDto;
import com.audieni.market.models.*;
import com.audieni.market.services.CartService;
import com.audieni.market.services.OrderService;
import com.audieni.market.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {
    private final UserService userService;
    private final CartService cartService;
    private final OrderService orderService;

    public OrderController(UserService userService, CartService cartService, OrderService orderService) {
        this.userService = userService;
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @Authorized
    @GetMapping
    public ResponseEntity<Optional<List<Order>>> findOrders(HttpSession session) {
        if (session.getAttribute("user") != null) {
            UserDto userDto = (UserDto) session.getAttribute("user");
            User user = userService.findByEmail(userDto.getEmail());
            return ResponseEntity.ok(orderService.findByUserId(user.getId()));
        }

        return ResponseEntity.badRequest().build();
    }

    @Authorized
    @PostMapping("/add")
    public ResponseEntity<Order> createOrder(HttpSession session) {
        if (session.getAttribute("user") != null) {
            UserDto userDto = (UserDto) session.getAttribute("user");
            User user = userService.findByEmail(userDto.getEmail());
            Optional<List<Cart>> cartList = cartService.findByUserId(user.getId());

            if (cartList.isPresent()) {
                List<OrderProductDto> productList = new ArrayList<>();

                for (Cart cart : cartList.get()) {
                    productList.add(new OrderProductDto(cart.getProduct(), cart.getStock()));
                    Optional<Cart> cartProduct = cartService.findByUserIdAndProductId(user.getId(),
                            cart.getProduct().getId());

                    if (cartProduct.isPresent()) {
                        cartService.delete(cart);
                    }
                }

                OrderDto order = new OrderDto(user, productList);
                return ResponseEntity.ok(orderService.save(order.toOrder()));
            }
        }

        return ResponseEntity.badRequest().build();
    }
}
