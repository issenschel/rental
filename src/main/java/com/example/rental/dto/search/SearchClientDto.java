package com.example.rental.dto.search;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SearchClientDto {
    @NotBlank(message = "name не может быть пустым")
    @Size(min = 2)
    String name;
}
