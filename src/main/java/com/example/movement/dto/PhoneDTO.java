package com.example.movement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneDTO {

    @NotBlank(message = "El número de teléfono no puede estar vacío")
    private String number;

    @NotBlank(message = "El código de ciudad no puede estar vacío")
    private String citycode;

    @NotBlank(message = "El código de país no puede estar vacío")
    private String countrycode;

}
