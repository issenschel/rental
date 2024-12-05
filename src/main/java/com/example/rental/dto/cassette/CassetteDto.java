package com.example.rental.dto.cassette;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class CassetteDto {
    Integer id;
    String name;
    String description;
}
