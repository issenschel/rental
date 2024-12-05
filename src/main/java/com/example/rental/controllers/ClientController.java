package com.example.rental.controllers;

import com.example.rental.dto.client.ClientDto;
import com.example.rental.dto.client.ClientFullDto;
import com.example.rental.entitys.Client;
import com.example.rental.services.ClientService;
import com.example.rental.services.ClientValidatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final ClientValidatorService clientValidatorService;

    @GetMapping()
    public ResponseEntity<?> searchClient(@RequestParam(name = "page") int page,@Valid @RequestParam(name = "client") String client){
        return ResponseEntity.ok().body(clientService.searchByString(client,page));
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<?> findClientById(@PathVariable int courseId){
        Optional<ClientFullDto> client = clientService.findClientById2(courseId);
        if(client.isPresent()){
            return ResponseEntity.ok().body(client.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Клиент не найден");
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewClient(@Valid @RequestBody ClientDto clientDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        Map<String, String> validationsErrors = clientValidatorService.validate(clientDto);
        if (!validationsErrors.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(validationsErrors);
        }
        return ResponseEntity.ok(clientService.createNewClient(clientDto));
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable int clientId) {
        boolean check = clientService.deleteById(clientId);
        if(check) {
            return ResponseEntity.ok().build();
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь не найден");
    }

}
