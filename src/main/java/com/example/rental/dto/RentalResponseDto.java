package com.example.rental.dto;

import com.example.rental.entitys.Rental;
import com.example.rental.enums.RentalStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RentalResponseDto {
    private int id;
    private String name;
    private RentalStatus rentalStatus;

    public RentalResponseDto(Rental rental) {
        this.id = rental.getId();
        this.name = rental.getCassette().getName();
        this.rentalStatus = rental.getStatus();
    }
}
