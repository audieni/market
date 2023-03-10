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
import java.util.Optional;

/**
 * Cart Controller to handle HTTP requests
 */
@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = {"http://localhost:4200", "http://129.80.51.149"}, allowCredentials = "true")
public class CartController {
    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;

    public CartController(UserService userService, ProductService productService, CartService cartService) {
        this.userService = userService;
        this.productService = productService;
        this.cartService = cartService;
    }

    /**
     * Finds all products in the user's cart
     * @param session Http session containing user info
     * @return Response with list of products in user's cart
     */
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

    /**
     * Add a product to user's cart
     * @param session Http session containing user info
     * @param productInfo Info about product in JSON form
     * @return Response with the added product
     */
    @Authorized
    @PostMapping("/add")
    public ResponseEntity<Cart> addProductToCart(HttpSession session, @RequestBody ProductDto productInfo) {
        int productId = productInfo.getProductId();
        int stock = productInfo.getStock();

        if (session.getAttribute("user") != null) {
            UserDto userDto = (UserDto) session.getAttribute("user");
            User user = userService.findByEmail(userDto.getEmail());
            Optional<Cart> cart = cartService.findByUserIdAndProductId(user.getUserId(), productId);

            if (cart.isPresent()) {
                cart.get().setStock(stock);
                return ResponseEntity.ok(cartService.save(cart.get()));
            } else {
                CartDto cartDto = new CartDto(user.getUserId(), productId, stock, userService, productService);
                Product product = productService.findById(productId);
                return ResponseEntity.ok(cartService.save(new Cart(cartDto.getUser(), product, stock)));
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
