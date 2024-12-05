package com.example.rental.dto.client;

import com.example.rental.entitys.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ClientInfoDto {
    private int id;
    private String name;
    private String surname;
    private String patronymic;


    public ClientInfoDto(Client client) {
        id = client.getId();
        name = client.getName();
        surname = client.getSurname();
        patronymic = client.getPatronymic();
    }
}
