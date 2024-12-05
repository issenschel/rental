package com.example.rental.entitys;

import com.example.rental.enums.RentalStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "cassette_id", referencedColumnName = "id")
    private Cassette cassette;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private RentalStatus status;

}
