package com.example.projet_skpeermahomed_nazif.service;

import com.example.projet_skpeermahomed_nazif.dto.AccountDTO;
import com.example.projet_skpeermahomed_nazif.dto.AmountDTO;
import com.example.projet_skpeermahomed_nazif.dto.TransferDTO;
import com.example.projet_skpeermahomed_nazif.entity.Account;
import com.example.projet_skpeermahomed_nazif.exception.InsufficientBalanceException;
import com.example.projet_skpeermahomed_nazif.exception.NotFoundException;
import com.example.projet_skpeermahomed_nazif.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public List<AccountDTO> findAll() {
        return accountRepository.findAll().stream().map(this::toDTO).toList();
    }

    public AccountDTO findById(Integer id) {
        return toDTO(getEntity(id));
    }

    public AccountDTO create(AccountDTO dto) {
        Account a = new Account();
        apply(dto, a);
        if (a.getAccountNumber() == null) {
            a.setAccountNumber(java.util.UUID.randomUUID());
        }
        if (a.getBalance() == null) {
            a.setBalance(BigDecimal.ZERO);
        }
        if (a.getDateOfCreation() == null) {
            a.setDateOfCreation(LocalDate.now());
        }
        return toDTO(accountRepository.save(a));
    }

    public AccountDTO update(Integer id, AccountDTO dto) {
        Account a = getEntity(id);
        apply(dto, a);
        return toDTO(accountRepository.save(a));
    }

    public void delete(Integer id) {
        if (!accountRepository.existsById(id)) {
            throw new NotFoundException("Account not found: " + id);
        }
        accountRepository.deleteById(id);
    }

    @Transactional
    public AccountDTO credit(Integer id, AmountDTO amountDTO) {
        Account a = getEntity(id);
        BigDecimal amount = requirePositive(amountDTO.amount());
        a.setBalance(a.getBalance().add(amount));
        return toDTO(a);
    }

    @Transactional
    public AccountDTO debit(Integer id, AmountDTO amountDTO) {
        Account a = getEntity(id);
        BigDecimal amount = requirePositive(amountDTO.amount());
        if (a.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance on account " + id);
        }
        a.setBalance(a.getBalance().subtract(amount));
        return toDTO(a);
    }

    @Transactional
    public void transfer(TransferDTO transferDTO) {
        if (transferDTO.fromAccountId().equals(transferDTO.toAccountId())) {
            return; // no-op
        }
        BigDecimal amount = requirePositive(transferDTO.amount());
        Account from = getEntity(transferDTO.fromAccountId());
        Account to = getEntity(transferDTO.toAccountId());
        if (from.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance on account " + from.getId());
        }
        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));
    }

    private BigDecimal requirePositive(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        return amount;
    }

    private Account getEntity(Integer id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Account not found: " + id));
    }

    private void apply(AccountDTO dto, Account a) {
        a.setTypeAccount(dto.typeAccount());
        a.setAccountNumber(dto.accountNumber());
        a.setBalance(dto.balance());
        a.setDateOfCreation(dto.dateOfCreation());
    }

    private AccountDTO toDTO(Account a) {
        return new AccountDTO(
                a.getId(),
                a.getTypeAccount(),
                a.getAccountNumber(),
                a.getBalance(),
                a.getDateOfCreation()
        );
    }
}
