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

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserInfo userInfo, HttpSession session)
            throws InvalidCredentialsException {
        Optional<UserDto> user = Optional.of(userService.authenticate(userInfo.getEmail(), userInfo.getPassword()));

        if (user.isPresent()) {
            session.setAttribute("user", user.get());
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.removeAttribute("user");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws ExistingUserException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }
}
