package com.example.projet_skpeermahomed_nazif.dto;

import com.example.projet_skpeermahomed_nazif.entity.enums.TypeWorker;

public record WorkerDTO(
        Integer id,
        TypeWorker typeWorker,
        String firstName,
        String lastName
) {}
