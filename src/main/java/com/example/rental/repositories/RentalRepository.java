package com.example.rental.repositories;

import com.example.rental.entitys.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Integer> {
    List<Rental> findAllByClientId(int clientId);
}
