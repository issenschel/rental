package com.example.rental.services;

import com.example.rental.dto.RentalRequestDto;
import com.example.rental.dto.RentalResponseDto;
import com.example.rental.dto.StringResponseDto;
import com.example.rental.entitys.Cassette;
import com.example.rental.entitys.Client;
import com.example.rental.entitys.Rental;
import com.example.rental.enums.RentalStatus;
import com.example.rental.repositories.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final ClientService clientService;
    private final CassetteService cassetteService;

    public List<RentalResponseDto> findAllByClientId(int clientId) {
        List<Rental> rentalList = rentalRepository.findAllByClientId(clientId);
        return rentalList.stream().map(RentalResponseDto::new).toList();
    }

    public StringResponseDto changeStatus(int rentalId) {
        Optional<Rental> rentalOptional = rentalRepository.findById(rentalId);
        if (rentalOptional.isPresent()) {
            Rental rental = rentalOptional.get();
            if(rental.getStatus() == RentalStatus.ACTIVE){
                rental.setStatus(RentalStatus.RETURNED);
            } else {
                rental.setStatus(RentalStatus.ACTIVE);
            }
            rentalRepository.save(rental);
            return new StringResponseDto("Статус изменен",HttpStatus.OK);
        }
        return new StringResponseDto("Прокат не найден", HttpStatus.NOT_FOUND);

    }

    public StringResponseDto addRental(RentalRequestDto rentalRequestDto) {
        Rental rental = new Rental();
        Optional<Client> client = clientService.findClientById(rentalRequestDto.getClientId());
        Optional<Cassette> cassette = cassetteService.findById(rentalRequestDto.getCassetteId());
        if(client.isPresent() && cassette.isPresent()) {
            rental.setClient(client.get());
            rental.setCassette(cassette.get());
            rental.setStatus(RentalStatus.ACTIVE);
            rentalRepository.save(rental);
            return new StringResponseDto("Всё успешно", HttpStatus.OK);
        }
        return  new StringResponseDto("Клиент или кассета не найдены", HttpStatus.NOT_FOUND);
    }
}
