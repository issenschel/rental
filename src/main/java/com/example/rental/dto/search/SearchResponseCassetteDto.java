package com.example.rental.dto.search;

import com.example.rental.dto.cassette.CassetteResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class SearchResponseCassetteDto {
    List<CassetteResponseDto> cassetteList;
    int count;
}
