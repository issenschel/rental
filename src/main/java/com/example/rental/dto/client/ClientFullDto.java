package com.example.rental.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ClientFullDto {
    private Integer id;
    private String name;
    private String surname;
    private String patronymic;
    private String phone;
    private String issued;
    private LocalDate issuedDate;
    private String passportSeries;
    private String passportNumber;
}
