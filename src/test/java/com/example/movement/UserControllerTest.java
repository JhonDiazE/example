package com.example.movement;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.movement.controller.UserController;
import com.example.movement.dto.UserDTO;
import com.example.movement.entity.User;
import com.example.movement.exceptions.AlreadyExistException;
import com.example.movement.mapper.UserMapper;
import com.example.movement.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser_Success() {
        UUID id = UUID.randomUUID();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setName("Juan Rodriguez");
        userDTO.setEmail("juan@rodriguez.org");
        userDTO.setPassword("hunter2");
        userDTO.setToken("some-jwt-token");

        User user = new User();
        user.setId(id);
        user.setName("Juan Rodriguez");
        user.setEmail("juan@rodriguez.org");
        user.setPassword("hunter2");
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setToken("some-jwt-token");
        user.setActive(true);

        when(userMapper.toEntity(userDTO)).thenReturn(user);
        when(userService.registerUser(userDTO)).thenReturn(userDTO);
        when(userMapper.toDTO(user)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.registerUser(userDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user.getId(), response.getBody().getId());
        assertEquals(user.getToken(), response.getBody().getToken());
    }

    @Test
    public void testRegisterUser_EmailAlreadyRegistered() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("juan@rodriguez.org");

        when(userMapper.toEntity(userDTO)).thenReturn(new User());
        when(userService.registerUser(any(UserDTO.class))).thenThrow(new AlreadyExistException("El correo ya registrado"));

        AlreadyExistException exception = assertThrows(
                AlreadyExistException.class,
                () -> userController.registerUser(userDTO)
        );
    }
}
