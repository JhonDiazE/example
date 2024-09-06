package com.example.movement.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private UUID id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El correo debe tener un formato válido")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,}$", message = "La contraseña debe tener al menos 8 caracteres y contener letras y números")
    private String password;

    @NotEmpty(message = "El listado de teléfonos no puede estar vacío")
    private List<PhoneDTO> phones;


    private String token;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private boolean isActive;

}
