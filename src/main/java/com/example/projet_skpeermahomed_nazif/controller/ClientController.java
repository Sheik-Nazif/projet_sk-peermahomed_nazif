package com.example.projet_skpeermahomed_nazif.controller;

import com.example.projet_skpeermahomed_nazif.dto.ClientDTO;
import com.example.projet_skpeermahomed_nazif.service.ClientService;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Tag(name = "Clients", description = "CRUD des clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    @Operation(summary = "Lister tous les clients")
    public List<ClientDTO> getAll() { return clientService.findAll(); }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un client par id")
    public ClientDTO getById(@PathVariable Integer id) { return clientService.findById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un client")
    public ClientDTO create(@RequestBody ClientDTO dto) { return clientService.create(dto); }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un client")
    public ClientDTO update(@PathVariable Integer id, @RequestBody ClientDTO dto) { return clientService.update(id, dto); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un client")
    public void delete(@PathVariable Integer id) { clientService.delete(id); }
}
