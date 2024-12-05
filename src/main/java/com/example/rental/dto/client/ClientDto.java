package com.example.rental.dto.client;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientDto {
    @NotBlank(message = "Не указано поле phone")
    @Pattern(regexp = "^[0-9]{7,12}$", message = "Неверный формат телефонного номера")
    private String phone;
    @NotBlank(message = "Не указано поле name")
    @Size(min = 1, max = 50, message = "Имя не может быть меньше 1 или больше 50")
    private String name;
    @NotBlank(message = "Не указано поле surname")
    @Size(min = 1, max = 50, message = "Фамилия не может быть меньше 1 или больше 50")
    private String surname;
    @NotNull(message = "Не указано поле patronymic")
    @Size(max = 50, message = "Отчество не может быть больше 50")
    private String patronymic;
    @NotNull(message = "Не указано поле issuedDate")
    @PastOrPresent(message = "Неправильно указана дата")
    private LocalDate issuedDate;
    @NotBlank(message = "Не указано поле issued")
    @Size(min = 1, max = 80, message = "Имя не может быть меньше 1 или больше 50")
    private String issued;
    @NotBlank(message = "Не указано поле passportSeries")
    @Pattern(regexp = "^[0-9]{4}$", message = "Неверный формат серии паспорта")
    private String passportSeries;
    @NotBlank(message = "Не указано поле passportNumber")
    @Pattern(regexp = "^[0-9]{6}$", message = "Неверный формат серии паспорта")
    private String passportNumber;
}
