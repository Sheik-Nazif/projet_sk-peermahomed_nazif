package com.example.projet_skpeermahomed_nazif.controller;

import com.example.projet_skpeermahomed_nazif.dto.AccountDTO;
import com.example.projet_skpeermahomed_nazif.dto.AmountDTO;
import com.example.projet_skpeermahomed_nazif.dto.TransferDTO;
import com.example.projet_skpeermahomed_nazif.service.AccountService;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@Tag(name = "Accounts", description = "CRUD des comptes + opérations (crédit, débit, virement)")
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    @Operation(summary = "Lister tous les comptes")
    public List<AccountDTO> getAll() { return accountService.findAll(); }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un compte par id")
    public AccountDTO getById(@PathVariable Integer id) { return accountService.findById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un compte")
    public AccountDTO create(@RequestBody AccountDTO dto) { return accountService.create(dto); }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un compte")
    public AccountDTO update(@PathVariable Integer id, @RequestBody AccountDTO dto) { return accountService.update(id, dto); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un compte")
    public void delete(@PathVariable Integer id) { accountService.delete(id); }

    @PostMapping("/{id}/credit")
    @Operation(summary = "Créditer un compte")
    public AccountDTO credit(@PathVariable Integer id, @RequestBody AmountDTO body) { return accountService.credit(id, body); }

    @PostMapping("/{id}/debit")
    @Operation(summary = "Débiter un compte")
    public AccountDTO debit(@PathVariable Integer id, @RequestBody AmountDTO body) { return accountService.debit(id, body); }

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Virement compte-à-compte (même banque)")
    public void transfer(@RequestBody TransferDTO dto) { accountService.transfer(dto); }
}
