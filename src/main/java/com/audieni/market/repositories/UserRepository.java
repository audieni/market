package com.audieni.market.repositories;

import com.audieni.market.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for working with users table
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Find a user by ID
     * @param id User's ID
     * @return Optional of user based on ID
     */
    Optional<User> findById(int id);

    /**
     * Find a user by email
     * @param email User's email
     * @return Optional of user based on email
     */
    Optional<User> findByEmail(String email);
}
