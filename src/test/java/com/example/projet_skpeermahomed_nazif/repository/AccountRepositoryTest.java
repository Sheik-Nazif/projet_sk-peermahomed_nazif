package com.example.projet_skpeermahomed_nazif.repository;

import com.example.projet_skpeermahomed_nazif.entity.Account;
import com.example.projet_skpeermahomed_nazif.entity.enums.TypeAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    private Account buildAccount() {
        return Account.builder()
                .typeAccount(TypeAccount.COURANT)
                .accountNumber(UUID.randomUUID())
                .balance(BigDecimal.ZERO)
                .dateOfCreation(LocalDate.now())
                .build();
    }

    @Test
    void createUpdateDeleteAccount() {
        // Create
        Account a = buildAccount();
        a = accountRepository.save(a);
        Assertions.assertNotNull(a.getId());

        // Update (credit)
        a.setBalance(a.getBalance().add(new BigDecimal("150.50")));
        Account updated = accountRepository.save(a);
        Assertions.assertEquals(new BigDecimal("150.50"), updated.getBalance());

        // Delete
        accountRepository.deleteById(updated.getId());
        Optional<Account> found = accountRepository.findById(updated.getId());
        Assertions.assertTrue(found.isEmpty());
    }
}
