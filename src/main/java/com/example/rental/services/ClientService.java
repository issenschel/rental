package com.example.rental.services;

import com.example.rental.dto.client.ClientDto;
import com.example.rental.dto.client.ClientFullDto;
import com.example.rental.dto.client.ClientInfoDto;
import com.example.rental.dto.PaginationClientsInfoDto;
import com.example.rental.entitys.Client;
import com.example.rental.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Client createNewClient(ClientDto clientDto){
        Client client = new Client();
        client.setName(clientDto.getName());
        client.setSurname(clientDto.getSurname());
        client.setIssued(clientDto.getIssued());
        client.setPhone(clientDto.getPhone());
        client.setIssuedDate(clientDto.getIssuedDate());
        client.setPassportSeries(clientDto.getPassportSeries());
        client.setPassportNumber(clientDto.getPassportNumber());
        if(!clientDto.getPatronymic().isBlank()){
            client.setPatronymic(clientDto.getPatronymic());
        }
       return clientRepository.save(client);
    }

    public PaginationClientsInfoDto searchByString(String searchBar, int id) {
        PaginationClientsInfoDto paginationClientsInfoDto = new PaginationClientsInfoDto();
        String[] searchTerms = searchBar.trim().split("\\s+");
        int termCount = searchTerms.length;
        Page<Client> clientPage;
        System.out.println(termCount);
        switch (termCount) {
            case 1:
                clientPage = clientRepository.findByOneFieldStartsWith(searchTerms[0], PageRequest.of(id,20, Sort.by("id")));
                break;
            case 2:
                clientPage = clientRepository.findByTwoFieldsStartsWith(searchTerms[0],searchTerms[1], PageRequest.of(id,20, Sort.by("id")));
                break;
            case 3:
                clientPage = clientRepository.findByThreeFieldsStartsWith(searchTerms[0],searchTerms[1],searchTerms[2], PageRequest.of(id,20, Sort.by("id")));
                break;
            default:
                return paginationClientsInfoDto;
        }
        paginationClientsInfoDto.setClientInfoDto(clientPage.stream().map(ClientInfoDto::new).toList());
        paginationClientsInfoDto.setNumberOfPages(clientPage.getTotalPages());
        return paginationClientsInfoDto;
    }

    public Optional<Client> findClientById(int id) {
        return clientRepository.findById(id);
    }

    public Optional<ClientFullDto> findClientById2(int id) {
        return clientRepository.findById2(id);
    }

    public Optional<Client> findByPassportNumberAndPassportSeries(String number, String series){
        return clientRepository.findByPassportNumberAndPassportSeries(number,series);
    }

    public Optional<Client> findByPhone(String phone) {
        return clientRepository.findByPhone(phone);
    }

    public boolean deleteById(int id){
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            clientRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
