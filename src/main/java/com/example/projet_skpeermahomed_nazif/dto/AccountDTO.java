package com.example.projet_skpeermahomed_nazif.dto;

import com.example.projet_skpeermahomed_nazif.entity.enums.TypeAccount;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record AccountDTO(
        Integer id,
        TypeAccount typeAccount,
        UUID accountNumber,
        BigDecimal balance,
        LocalDate dateOfCreation
) {}
