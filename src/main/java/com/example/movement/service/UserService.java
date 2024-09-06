package com.example.movement.service;


import com.example.movement.dto.UserDTO;
import com.example.movement.entity.User;
import com.example.movement.exceptions.AlreadyExistException;
import com.example.movement.mapper.UserMapper;
import com.example.movement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    public UserDTO registerUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new AlreadyExistException("El correo ya registrado");
        }

        String token = jwtService.generateToken(user);
        user.setToken(token);

        userRepository.save(user);
        return userMapper.toDTO(user);
    }
}
