package com.example.projet_skpeermahomed_nazif.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "worker")
@Entity
@Table(name = "agence")
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agence_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "worker_id", unique = true, nullable = false)
    private Worker worker;

    @Column(name = "identification_number", nullable = false, unique = true)
    private UUID identificationNumber;

    @Column(name = "date_of_creation", nullable = false)
    private LocalDate dateOfCreation;
}
