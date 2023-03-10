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

/**
 * Order Controller to handle HTTP request
 */
@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = {"http://localhost:4200", "http://129.80.51.149"}, allowCredentials = "true")
public class OrderController {
    private final UserService userService;
    private final CartService cartService;
    private final OrderService orderService;

    public OrderController(UserService userService, CartService cartService,
                           OrderService orderService) {
        this.userService = userService;
        this.cartService = cartService;
        this.orderService = orderService;
    }

    /**
     * Find all orders from user
     * @param session Http session containing user info
     * @return Response with a list of user's orders
     */
    @Authorized
    @GetMapping
    public ResponseEntity<List<Order>> findOrders(HttpSession session) {
        if (session.getAttribute("user") != null) {
            UserDto userDto = (UserDto) session.getAttribute("user");
            User user = userService.findByEmail(userDto.getEmail());
            return ResponseEntity.ok(orderService.findByUserId(user.getUserId()).orElse(new ArrayList<>()));
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Create new order with products in user's cart
     * @param session Http session containing user info
     * @return Response with newly created order
     */
    @Authorized
    @PostMapping("/add")
    public ResponseEntity<Order> createOrder(HttpSession session) {
        if (session.getAttribute("user") != null) {
            UserDto userDto = (UserDto) session.getAttribute("user");
            User user = userService.findByEmail(userDto.getEmail());
            Optional<List<Cart>> cartList = cartService.findByUserId(user.getUserId());

            if (cartList.isPresent()) {
                List<OrderProductDto> productList = new ArrayList<>();

                for (Cart cart : cartList.get()) {
                    productList.add(new OrderProductDto(cart.getProduct(), cart.getStock()));
                    Optional<Cart> cartProduct = cartService.findByUserIdAndProductId(user.getUserId(),
                            cart.getProduct().getProductId());

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

    /**
     * Get list of products in order by ID
     * @param session Http session containing user info
     * @param orderId Order ID according to database
     * @return Response with all products in order with Order ID
     */
    @Authorized
    @GetMapping("/{orderId}")
    public ResponseEntity<Optional<List<OrderProduct>>> findProducts(HttpSession session,
                                                                     @PathVariable("orderId") int orderId) {
        if (session.getAttribute("user") != null) {
            return ResponseEntity.ok(orderService.findByOrderId(orderId));
        }
        return ResponseEntity.badRequest().build();
    }
}
