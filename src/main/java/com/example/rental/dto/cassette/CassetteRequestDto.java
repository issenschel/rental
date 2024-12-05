package com.example.rental.dto.cassette;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CassetteRequestDto {
    @NotBlank(message = "Не указано поле name")
    @Size(min = 1, max = 50, message = "Имя не может быть меньше 1 или больше 50")
    private String name;

    @NotBlank(message = "Не указано поле name")
    @Size(min = 1, max = 150, message = "Имя не может быть меньше 1 или больше 50")
    private String description;
}
