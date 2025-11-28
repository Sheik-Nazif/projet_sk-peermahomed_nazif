package com.example.projet_skpeermahomed_nazif.dto;

public record ClientDTO(
        Integer id,
        String firstName,
        String lastName,
        String address,
        String postal,
        String city,
        String phoneNumber,
        Integer workerId,
        Integer courantAccountId,
        Integer epargnAccountId
) {}
