package com.audieni.market.controllers;

import com.audieni.market.annotations.Authorized;
import com.audieni.market.dtos.CartDto;
import com.audieni.market.dtos.ProductDto;
import com.audieni.market.dtos.UserDto;
import com.audieni.market.models.Cart;
import com.audieni.market.models.Product;
import com.audieni.market.models.User;
import com.audieni.market.services.CartService;
import com.audieni.market.services.ProductService;
import com.audieni.market.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
public class CartController {
    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;

    public CartController(UserService userService, ProductService productService, CartService cartService) {
        this.userService = userService;
        this.productService = productService;
        this.cartService = cartService;
    }

    @Authorized
    @GetMapping
    public ResponseEntity<Optional<List<Cart>>> findAllCartProducts(HttpSession session) {
        if (session.getAttribute("user") != null) {
            UserDto userDto = (UserDto) session.getAttribute("user");
            User user = userService.findByEmail(userDto.getEmail());
            return ResponseEntity.ok(cartService.findByUserId(user.getUserId()));
        }

        return ResponseEntity.badRequest().build();
    }

    @Authorized
    @PostMapping("/add")
    public ResponseEntity<Cart> addProductToCart(HttpSession session, @RequestBody ProductDto productInfo) {
        int productId = productInfo.getProductId();
        int stock = productInfo.getStock();

        System.out.println("a" + productId);

        if (session.getAttribute("user") != null) {
            UserDto userDto = (UserDto) session.getAttribute("user");
            User user = userService.findByEmail(userDto.getEmail());

            System.out.println("b");

            if (cartService.findByUserIdAndProductId(user.getUserId(), productId).isPresent()) {
                Optional<Cart> cart = cartService.findByUserIdAndProductId(user.getUserId(), productId);
                cart.get().setStock(stock);
                System.out.println("c" + cart.get().getProduct().getProductId());
                return ResponseEntity.ok(cartService.save(cart.get()));
            } else {
                CartDto cart = new CartDto(user.getUserId(), productId, stock, userService, productService);
                Product product = productService.findById(productId);
                System.out.println("d" + product.getProductId());
                return ResponseEntity.ok(cartService.save(new Cart(cart.getUser(), product, stock)));
            }
        }

        return ResponseEntity.badRequest().build();
    }
}
