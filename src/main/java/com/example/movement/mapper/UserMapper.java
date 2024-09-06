package com.example.movement.mapper;

import com.example.movement.dto.PhoneDTO;
import com.example.movement.dto.UserDTO;
import com.example.movement.entity.Phone;
import com.example.movement.entity.User;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserMapper {


    public UserDTO toDTO(User user) {
        if (Objects.isNull(user)) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setPhones(user.getPhones().stream()
                .map(this::toDTO)
                .toList());

        return userDTO;
    }

    public User toEntity(UserDTO userDTO) {
        if (Objects.isNull(userDTO)) {
            return null;
        }

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhones(userDTO.getPhones().stream()
                .map(this::toEntity)
                .toList());

        return user;
    }

    private PhoneDTO toDTO(Phone phone) {
        if (Objects.isNull(phone)) {
            return null;
        }

        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setNumber(phone.getNumber());
        phoneDTO.setCitycode(phone.getCitycode());
        phoneDTO.setCountrycode(phone.getCountrycode());

        return phoneDTO;
    }

    private Phone toEntity(PhoneDTO phoneDTO) {
        if (Objects.isNull(phoneDTO)) {
            return null;
        }

        Phone phone = new Phone();
        phone.setNumber(phoneDTO.getNumber());
        phone.setCitycode(phoneDTO.getCitycode());
        phone.setCountrycode(phoneDTO.getCountrycode());

        return phone;
    }
}
