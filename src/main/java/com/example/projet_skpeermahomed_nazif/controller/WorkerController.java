package com.example.projet_skpeermahomed_nazif.controller;

import com.example.projet_skpeermahomed_nazif.dto.WorkerDTO;
import com.example.projet_skpeermahomed_nazif.service.WorkerService;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workers")
@RequiredArgsConstructor
@Tag(name = "Workers", description = "CRUD des conseillers")
public class WorkerController {

    private final WorkerService workerService;

    @GetMapping
    @Operation(summary = "Lister tous les conseillers")
    public List<WorkerDTO> getAll() { return workerService.findAll(); }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un conseiller par id")
    public WorkerDTO getById(@PathVariable Integer id) { return workerService.findById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un conseiller")
    public WorkerDTO create(@RequestBody WorkerDTO dto) { return workerService.create(dto); }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un conseiller")
    public WorkerDTO update(@PathVariable Integer id, @RequestBody WorkerDTO dto) { return workerService.update(id, dto); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un conseiller")
    public void delete(@PathVariable Integer id) { workerService.delete(id); }
}
