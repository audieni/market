package com.audieni.market.controllers;

import com.audieni.market.dtos.UserInfo;
import com.audieni.market.dtos.UserDto;
import com.audieni.market.exceptions.ExistingUserException;
import com.audieni.market.exceptions.InvalidCredentialsException;
import com.audieni.market.models.User;
import com.audieni.market.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * User Controller to handle HTTP request
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {"http://localhost:4200", "http://129.80.51.149"}, allowCredentials = "true")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Login user if credentials are correct
     * @param userInfo User's email and password
     * @param session Http session containing user info
     * @return Response with user's information excluding the password
     * @throws InvalidCredentialsException Thrown if user's credentials are incorrect
     */
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserInfo userInfo, HttpSession session)
            throws InvalidCredentialsException {
        Optional<UserDto> user = Optional.of(userService.authenticate(userInfo.getEmail(), userInfo.getPassword()));
        session.setAttribute("user", user.get());
        return ResponseEntity.ok(user.get());
    }

    /**
     * Logout user
     * @param session Http session containing user info
     * @return Response with OK status
     */
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.removeAttribute("user");
        return ResponseEntity.ok().build();
    }

    /**
     * Register a user
     * @param user User's information in request body
     * @return Information saved into database for user account
     */
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    /**
     * Gets info on current user
     * @param session Http session containing user info
     * @return Response with user's info excluding the password
     */
    @GetMapping("/me")
    public ResponseEntity<UserDto> me(HttpSession session) {
        if (session.getAttribute("user") != null) {
            UserDto userDto = (UserDto) session.getAttribute("user");
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
