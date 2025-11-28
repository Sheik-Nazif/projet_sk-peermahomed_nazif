package com.example.projet_skpeermahomed_nazif.controller;

import com.example.projet_skpeermahomed_nazif.dto.AuditReportDTO;
import com.example.projet_skpeermahomed_nazif.service.AuditService;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/audit")
@RequiredArgsConstructor
@Tag(name = "Audit", description = "Rapports d'audit des comptes")
public class AuditController {

    private final AuditService auditService;

    @GetMapping("/accounts")
    @Operation(summary = "Rapport des comptes créditeurs/débiteurs et totaux")
    public AuditReportDTO accountsReport() {
        return auditService.buildAccountsReport();
    }
}
