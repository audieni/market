package com.audieni.market.services;

import com.audieni.market.dtos.UserDto;
import com.audieni.market.exceptions.InvalidCredentialsException;
import com.audieni.market.models.User;
import com.audieni.market.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User service to handle requests between controllers and repositories
 */
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Authenticates user's credentials
     * @param email User's email
     * @param password User's password
     * @return User's info excluding the password
     * @throws InvalidCredentialsException Thrown if credentials are invalid
     */
    public UserDto authenticate(String email, String password) throws InvalidCredentialsException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return new UserDto(user.get());
        } else {
            throw new InvalidCredentialsException();
        }
    }

    /**
     * FInd a user by ID
     * @param id User's ID
     * @return User based on ID
     */
    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Find a user by email
     * @param email User's email
     * @return User based on email
     */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    /**
     * Save info on user
     * @param user User object
     * @return Newly created user
     */
    public User save(User user) {
        return userRepository.save(user);
    }
}
