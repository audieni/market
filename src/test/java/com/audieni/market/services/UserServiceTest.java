package com.audieni.market.services;

import com.audieni.market.dtos.UserDto;
import com.audieni.market.models.User;
import com.audieni.market.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    public UserService sut;

    @Mock
    private User mockUser;

    @Mock
    UserRepository mockUserRepository;

    private final String email = "user@gmail.com";
    private final String password = "password";
    private final int id = 1;

    @Test
    void findByIdTest() {
        sut = new UserService(mockUserRepository);
        Mockito.when(mockUserRepository.findById(id)).thenReturn(Optional.of(mockUser));
        User user = sut.findById(id);
        Assertions.assertEquals(Optional.of(mockUser).get(), user);
    }

    @Test
    void findByEmailTest() {
        sut = new UserService(mockUserRepository);
        Mockito.when(mockUserRepository.findByEmail(email)).thenReturn(Optional.of(mockUser));
        Optional<User> user = Optional.of(sut.findByEmail(email));
        Assertions.assertEquals(Optional.of(mockUser), user);
    }

    @Test
    void saveTest() {
        sut = new UserService(mockUserRepository);
        Mockito.when(mockUserRepository.save(mockUser)).thenReturn(mockUser);
        Optional<User> user = Optional.of(sut.save(mockUser));
        Assertions.assertEquals(Optional.of(mockUser), user);
    }
}
