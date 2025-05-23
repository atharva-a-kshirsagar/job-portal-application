package com.capgemini.job_application;

import com.capgemini.job_application.controllers.UserController;
import com.capgemini.job_application.entities.User;
import com.capgemini.job_application.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Mock
    private BindingResult bindingResult;

    private User sampleUser;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        sampleUser = new User(1L, "John Doe", "john@example.com", "1234567890",
                "password", "123 Main St", "USER", 25, "Male");
    }

    @Test
    void testGetAllUsers() {
        List<User> users = Arrays.asList(sampleUser);
        when(userService.getAllUsers()).thenReturn(users);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("John Doe", response.getBody().get(0).getUserName());
    }

    @Test
    void testGetUserById() {
        when(userService.getUserById(1L)).thenReturn(sampleUser);

        ResponseEntity<User> response = userController.getUser(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("john@example.com", response.getBody().getUserEmail());
    }

    @Test
    void testCreateUser() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.createUser(any(User.class))).thenReturn(sampleUser);

        ResponseEntity<User> response = userController.createUser(sampleUser, bindingResult);

        assertEquals(201, response.getStatusCode().value());
        assertEquals("John Doe", response.getBody().getUserName());
    }

    


    @Test
    void testDeleteUser() {
        doNothing().when(userService).deleteUser(1L);

        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(userService, times(1)).deleteUser(1L);
    }
}
