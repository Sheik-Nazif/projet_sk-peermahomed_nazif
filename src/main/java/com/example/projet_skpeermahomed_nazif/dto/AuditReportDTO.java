package com.example.projet_skpeermahomed_nazif.dto;

import java.math.BigDecimal;
import java.util.List;

public record AuditReportDTO(
        List<AccountSummaryDTO> creditors,
        List<AccountSummaryDTO> debtors,
        BigDecimal totalCredit,
        BigDecimal totalDebit
) {}
