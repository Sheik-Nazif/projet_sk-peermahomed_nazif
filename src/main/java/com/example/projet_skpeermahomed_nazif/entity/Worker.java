package com.example.projet_skpeermahomed_nazif.entity;

import com.example.projet_skpeermahomed_nazif.entity.enums.TypeWorker;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "worker")
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "worker_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_worker", nullable = false)
    private TypeWorker typeWorker;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;
}
