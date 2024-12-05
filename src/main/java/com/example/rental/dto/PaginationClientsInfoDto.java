package com.example.rental.dto;

import com.example.rental.dto.client.ClientInfoDto;
import lombok.Data;

import java.util.List;

@Data
public class PaginationClientsInfoDto {
    List<ClientInfoDto> clientInfoDto;
    int numberOfPages;
}
