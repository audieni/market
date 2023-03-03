package com.audieni.market.repositories;

import com.audieni.market.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setup() {
        user = User.builder()
                .id(1)
                .email("user@gmail.com")
                .password("password")
                .country("US")
                .build();
    }

    @Test
    void findByIdTest() {
        userRepository.save(user);
        Optional<User> returnedUser = userRepository.findById(1);
        Assertions.assertNotNull(returnedUser);
    }

    @Test
    void findByEmailTest() {
        userRepository.save(user);
        Optional<User> returnedUser = userRepository.findByEmail("user@gmail.com");
        Assertions.assertNotNull(returnedUser);
    }
}
