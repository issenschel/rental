package com.example.rental.services;

import com.example.rental.dto.client.ClientDto;
import com.example.rental.interfaces.ValidationRule;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientValidatorService {
    private final ClientService clientService;

    private final Map<String, ValidationRule> validationRules = new HashMap<>();

    @PostConstruct
    public void init() {
        validationRules.put("username", this::validateClientPassportNumber);
        validationRules.put("phone", this::validatePhone);
    }

    public Map<String, String> validate(ClientDto clientDto) {
        Map<String, String> errors = new HashMap<>();

        validationRules.forEach((field, rule) ->
                rule.validate(clientDto).ifPresent(errorMessage -> errors.put(field, errorMessage)));

        return errors;
    }

    public Optional<String> validateClientPassportNumber(ClientDto clientDto) {
        return clientService.findByPassportNumberAndPassportSeries(clientDto.getPassportNumber(), clientDto.getPassportSeries())
                .map(user -> "Номер паспорта уже занят");
    }

    public Optional<String> validatePhone(ClientDto clientDto) {
        return clientService.findByPhone(clientDto.getPhone())
                .map(user -> "Телефон занят");
    }

}


