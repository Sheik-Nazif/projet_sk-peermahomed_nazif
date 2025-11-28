package com.example.projet_skpeermahomed_nazif.entity;

import com.example.projet_skpeermahomed_nazif.entity.enums.TypeAccount;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_account", nullable = false)
    private TypeAccount typeAccount;

    @Column(name = "account_number", nullable = false, unique = true)
    private UUID accountNumber;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "date_of_creation", nullable = false)
    private LocalDate dateOfCreation;
}
