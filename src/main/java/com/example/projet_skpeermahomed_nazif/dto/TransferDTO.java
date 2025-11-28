package com.example.projet_skpeermahomed_nazif.dto;

import java.math.BigDecimal;

public record TransferDTO(Integer fromAccountId, Integer toAccountId, BigDecimal amount) {
}
