package com.capgemini.job_application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import com.capgemini.job_application.entities.User;
import com.capgemini.job_application.repositories.UserRepository;
import com.capgemini.job_application.services.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "Alice", "alice@example.com", "1234567890", "password", "Address", "USER", 25, "Female");
    }

    @Test
    void getAllUsers_shouldReturnUserList() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        List<User> result = userService.getAllUsers();
        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getUserName());
    }

    @Test
    void getUserById_whenUserExists_shouldReturnUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User result = userService.getUserById(1L);
        assertEquals("Alice", result.getUserName());
    }

    @Test
    void getUserById_whenUserDoesNotExist_shouldThrowException() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        Exception ex = assertThrows(RuntimeException.class, () -> userService.getUserById(2L));
        assertTrue(ex.getMessage().contains("No user found with ID: 2"));
    }

    @Test
    void updateUser_whenUserExists_shouldUpdateAndReturnUser() {
        User updated = new User(1L, "Bob", "bob@example.com", "0987654321", "newpass", "New Address", "ADMIN", 30, "Male");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(updated);

        User result = userService.updateUser(1L, updated);
        assertEquals("Bob", result.getUserName());
        assertEquals("bob@example.com", result.getUserEmail());
    }

    @Test
    void updateUser_whenUserDoesNotExist_shouldThrowException() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        User updated = new User();
        Exception ex = assertThrows(RuntimeException.class, () -> userService.updateUser(2L, updated));
        assertTrue(ex.getMessage().contains("No user found with ID: 2"));
    }

    @Test
    void deleteUser_shouldCallRepositoryDelete() {
        User user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).delete(user);
    }


    @Test
    void createUser_shouldSaveAndReturnUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);
        User result = userService.createUser(user);
        assertEquals("Alice", result.getUserName());
    }
}

