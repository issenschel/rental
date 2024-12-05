package com.example.rental.interfaces;

import com.example.rental.dto.client.ClientDto;

import java.util.Optional;

public interface ValidationRule {
    Optional<String> validate(ClientDto clientDto);
}
