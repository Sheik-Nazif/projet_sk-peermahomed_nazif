package com.example.projet_skpeermahomed_nazif.service;

import com.example.projet_skpeermahomed_nazif.dto.AccountSummaryDTO;
import com.example.projet_skpeermahomed_nazif.dto.AuditReportDTO;
import com.example.projet_skpeermahomed_nazif.entity.Account;
import com.example.projet_skpeermahomed_nazif.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AccountRepository accountRepository;

    public AuditReportDTO buildAccountsReport() {
        List<Account> accounts = accountRepository.findAll();

        List<AccountSummaryDTO> creditors = accounts.stream()
                .filter(a -> a.getBalance() != null && a.getBalance().compareTo(BigDecimal.ZERO) > 0)
                .map(a -> new AccountSummaryDTO(a.getId(), a.getBalance()))
                .toList();

        List<AccountSummaryDTO> debtors = accounts.stream()
                .filter(a -> a.getBalance() != null && a.getBalance().compareTo(BigDecimal.ZERO) < 0)
                .map(a -> new AccountSummaryDTO(a.getId(), a.getBalance()))
                .toList();

        BigDecimal totalCredit = creditors.stream()
                .map(AccountSummaryDTO::balance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalDebit = debtors.stream()
                .map(AccountSummaryDTO::balance)
                .map(BigDecimal::abs)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new AuditReportDTO(creditors, debtors, totalCredit, totalDebit);
    }
}
