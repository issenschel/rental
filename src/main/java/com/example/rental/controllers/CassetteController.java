package com.example.rental.controllers;

import com.example.rental.dto.cassette.CassetteDto;
import com.example.rental.dto.cassette.CassetteRequestDto;
import com.example.rental.dto.search.SearchResponseCassetteDto;
import com.example.rental.entitys.Cassette;
import com.example.rental.services.CassetteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/cassette")
@RequiredArgsConstructor
public class CassetteController {
    private final CassetteService cassetteService;

    @GetMapping()
    public ResponseEntity<?> searchCassette(@RequestParam(name = "page") int page,@RequestParam(name = "cassette") String cassette){
        SearchResponseCassetteDto searchResponseCassetteDto = cassetteService.findBySearchTerm(cassette,page);
        return ResponseEntity.ok().body(searchResponseCassetteDto);
    }

    @PostMapping()
    public ResponseEntity<?> createNewCassette(@RequestParam("file") MultipartFile file, @Valid @ModelAttribute CassetteRequestDto cassetteRequestDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        if (cassetteService.findByName(cassetteRequestDto.getName()).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Такой фильм уже есть");
        }
        try {
            Cassette cassette = cassetteService.createNewCassette(file, cassetteRequestDto);
            return ResponseEntity.ok().body(cassette);
        } catch (IOException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e);
        }
    }

    @DeleteMapping("/{cassetteId}")
    public ResponseEntity<?> deleteCassette(@PathVariable int cassetteId) {
        boolean check = cassetteService.deleteById(cassetteId);
        if(check) {
            return ResponseEntity.ok().build();
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Фильм не найден");
    }

    @GetMapping("/{cassetteId}")
    public ResponseEntity<?> searchCassetteById(@PathVariable int cassetteId){
        Optional<CassetteDto> cassetteOptional = cassetteService.findById2(cassetteId);
        if(cassetteOptional.isPresent()){
            return ResponseEntity.ok().body(cassetteOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Фильм не найден");
    }

    @GetMapping("/getPhoto/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable int id) throws Exception {
        Path path = cassetteService.getPhoto(id);
        Resource resource = new UrlResource(path.toUri());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}
