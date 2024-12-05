package com.example.rental.services;

import com.example.rental.dto.cassette.CassetteDto;
import com.example.rental.dto.cassette.CassetteRequestDto;
import com.example.rental.dto.cassette.CassetteResponseDto;
import com.example.rental.dto.search.SearchResponseCassetteDto;
import com.example.rental.entitys.Cassette;
import com.example.rental.repositories.CassetteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CassetteService {
    private final CassetteRepository cassetteRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public SearchResponseCassetteDto findBySearchTerm(String searchTerm,int page){
        SearchResponseCassetteDto srDto = new SearchResponseCassetteDto();
        PageRequest pageRequest = PageRequest.of(page, 20);
        Page<Cassette> cassettesPage = cassetteRepository.findBySearchTerm(searchTerm, pageRequest);
        srDto.setCassetteList(cassettesPage.stream().map(CassetteResponseDto::new).toList());
        srDto.setCount(cassettesPage.getTotalPages());
        return srDto;
    }

    public Cassette createNewCassette(MultipartFile multipartFile, CassetteRequestDto cassetteRequestDto) throws IOException {
        Cassette cassette = new Cassette();
        cassette.setName(cassetteRequestDto.getName());
        cassette.setDescription(cassetteRequestDto.getDescription());
        String uuidFile = UUID.randomUUID().toString();
        String result = uuidFile + "." + multipartFile.getOriginalFilename();
        multipartFile.transferTo(new File(uploadPath + "/" + result));
        cassette.setPhoto(result);
        return cassetteRepository.save(cassette);
    }

    public boolean deleteById(int id){
        Optional<Cassette> clientOptional = cassetteRepository.findById(id);
        if (clientOptional.isPresent()) {
            cassetteRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<Cassette> findById(int id){
        return cassetteRepository.findById(id);
    }

    public Optional<CassetteDto> findById2(int id){
        return cassetteRepository.findById2(id);
    }

    public Path getPhoto(int id){
        String photo = cassetteRepository.getPhoto(id);
        return Paths.get("/userPhoto/" + photo);
    }

    public Optional<Cassette> findByName(String name){
        return cassetteRepository.findByName(name);
    }
}
