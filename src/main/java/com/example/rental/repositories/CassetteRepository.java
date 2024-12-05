package com.example.rental.repositories;

import com.example.rental.dto.cassette.CassetteDto;
import com.example.rental.dto.client.ClientFullDto;
import com.example.rental.entitys.Cassette;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CassetteRepository extends CrudRepository<Cassette, Integer> {
    @Query("SELECT c FROM Cassette c WHERE "
            + "LOWER(c.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ")
    Page<Cassette> findBySearchTerm(String searchTerm, Pageable pageable);

    Optional<Cassette> findByName(String name);

    @Query("SELECT c.photo FROM Cassette c WHERE c.id = :id ")
    String getPhoto(int id);

    @Query("SELECT new com.example.rental.dto.cassette.CassetteDto(c.id,c.name,c.description) FROM Cassette c WHERE c.id = :i")
    Optional<CassetteDto> findById2(int i);
}
