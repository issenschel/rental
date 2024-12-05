package com.example.rental.dto.cassette;

import com.example.rental.entitys.Cassette;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CassetteResponseDto {
    private int cassetteId;
    private String name;

    public CassetteResponseDto(Cassette cassette) {
        cassetteId = cassette.getId();
        name = cassette.getName();
    }
}
