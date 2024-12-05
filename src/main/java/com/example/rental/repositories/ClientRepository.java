package com.example.rental.repositories;

import com.example.rental.dto.client.ClientFullDto;
import com.example.rental.entitys.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query("SELECT c FROM Client c WHERE " +
            "LOWER(c.surname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(c.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(c.patronymic) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Client> findByOneFieldStartsWith(@Param("searchTerm")String searchTerm, Pageable pageable);

    @Query("SELECT c FROM Client c WHERE " +
            "(LOWER(c.surname) LIKE LOWER(CONCAT('%', :searchTerm1, '%')) AND LOWER(c.name) LIKE LOWER(CONCAT('%', :searchTerm2, '%'))) OR " +
            "(LOWER(c.name) LIKE LOWER(CONCAT('%', :searchTerm1, '%')) AND LOWER(c.patronymic) LIKE LOWER(CONCAT('%', :searchTerm2, '%')))")
    Page<Client> findByTwoFieldsStartsWith(@Param("searchTerm")String searchTerm1, @Param("searchTerm2")String searchTerm2, Pageable pageable);

    @Query("SELECT c FROM Client c WHERE " +
            "(LOWER(c.surname) LIKE LOWER(CONCAT('%', :searchTerm1, '%')) AND LOWER(c.name) LIKE LOWER(CONCAT('%', :searchTerm2, '%')) " +
            "AND LOWER(c.patronymic) LIKE LOWER(CONCAT('%', :searchTerm3, '%')))")
    Page<Client> findByThreeFieldsStartsWith(@Param("searchTerm")String searchTerm1,@Param("searchTerm2")String searchTerm2,
                                             @Param("searchTerm3")String searchTerm3, Pageable pageable);

    Optional<Client> findByPassportNumberAndPassportSeries(String number, String series);

    Optional<Client> findByPhone(String phone);

    @Query("SELECT new com.example.rental.dto.client.ClientFullDto(c.id,c.name,c.surname,c.patronymic,c.phone,c.issued,c.issuedDate,c.passportSeries,c.passportNumber) FROM Client c WHERE c.id = :i")
    Optional<ClientFullDto> findById2(int i);
}
