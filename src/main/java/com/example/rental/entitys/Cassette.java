package com.example.rental.entitys;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "cassettes")
public class Cassette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "photo")
    private String photo;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "cassette", cascade = CascadeType.REMOVE) // Добавлено каскадное удаление
    private List<Rental> rentals;
}
