package com.example.projet_skpeermahomed_nazif.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "SimpleCash API",
                version = "v1",
                description = "API de gestion bancaire pédagogique (clients, comptes, conseillers, virements, audit)",
                contact = @Contact(name = "SK-PeerMahomed Nazif")
        ),
        servers = {
                @Server(url = "/", description = "Serveur par défaut")
        }
)
public class OpenAPIConfig {
}
