package com.example.rental.controllers;

import com.example.rental.dto.RentalRequestDto;
import com.example.rental.dto.RentalResponseDto;
import com.example.rental.dto.StringResponseDto;
import com.example.rental.services.RentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/rental")
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    @GetMapping("/{clientId}")
    public ResponseEntity<?> findClientById(@PathVariable int clientId){
        List<RentalResponseDto> rentalList = rentalService.findAllByClientId(clientId);
        if(!rentalList.isEmpty()){
            return ResponseEntity.ok().body(rentalList);
        }
        StringResponseDto responseDto = new StringResponseDto(null, HttpStatus.NOT_FOUND);
        return ResponseEntity.status(responseDto.getStatus()).body(responseDto);
    }

    @PutMapping("/{rentalId}")
    public ResponseEntity<?> changeStatus(@PathVariable int rentalId){
        StringResponseDto stringResponseDto = rentalService.changeStatus(rentalId);
        return ResponseEntity.status(stringResponseDto.getStatus()).body(stringResponseDto.getMessage());
    }

    @PostMapping()
    public ResponseEntity<?> addRental(@Valid @RequestBody RentalRequestDto rentalRequestDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        StringResponseDto stringResponseDto = rentalService.addRental(rentalRequestDto);
        return ResponseEntity.status(stringResponseDto.getStatus()).body(stringResponseDto.getMessage());

    }
}
