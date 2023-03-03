package com.audieni.market.services;

import com.audieni.market.dtos.UserDto;
import com.audieni.market.exceptions.InvalidCredentialsException;
import com.audieni.market.models.User;
import com.audieni.market.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto authenticate(String email, String password) throws InvalidCredentialsException {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return new UserDto(user.get());
        } else {
            throw new InvalidCredentialsException();
        }
    }

    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
