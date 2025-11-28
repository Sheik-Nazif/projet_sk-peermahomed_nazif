package com.example.projet_skpeermahomed_nazif.repository;

import com.example.projet_skpeermahomed_nazif.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
