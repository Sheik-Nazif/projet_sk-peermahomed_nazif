# projet_sk-peermahomed_nazif

# UML – Architecture du projet SimpleCash

 - Architecture UML
 - Diagramme métier
 - User stories
 - Installation
 - Tech stack
 - Endpoints
 - Logging AOP
 - Auteur

 ## Swagger / OpenAPI

 La documentation interactive de l'API est disponible via Swagger UI.

 - UI: `http://localhost:8080/swagger-ui` (ou `http://localhost:8080/swagger-ui/index.html`)
 - Spécification OpenAPI (JSON): `http://localhost:8080/v3/api-docs`

 Les endpoints sont annotés avec des `@Tag` et `@Operation` pour fournir des titres et descriptions claires.

---

# **README.md — VERSION FINALE (COMPLÈTE)**

````markdown
# Projet SimpleCash – SK-PeerMahomed Nazif
Application bancaire pédagogique réalisée avec **Spring Boot 3.5.8**, **Java 21**, **JPA**, et **Maven**.

Le projet SimpleCash permet de gérer :
- les **clients**
- les **comptes bancaires**
- les **conseillers (workers)**
- les **agences**
- les **virements**
- l’**audit des comptes**

Ce projet correspond au système d’information **SimpleCashSI**.

---
````
# 1) Architecture du projet (UML)

Architecture en couches :

**Controller → Service → Repository → Entity**

```mermaid
classDiagram
    class controller {
    <<layer>>
    +ClientController
    +AccountController
    +WorkerController
    +AgenceController
    }

    class service {
    <<layer>>
    +ClientService
    +AccountService
    +WorkerService
    +AgenceService
    }

    class repository {
    <<layer>>
    +ClientRepository
    +AccountRepository
    +WorkerRepository
    +AgenceRepository
    }

    class entity {
    <<layer>>
    +Client
    +Account
    +Worker
    +Agence
    }

    controller --> service : "appelle"
    service --> repository : "utilise"
    repository --> entity : "manipule"
```

---

# 2) Modèle UML – Vue métier (classes)

```mermaid
classDiagram
    class Agence {
        +agence_id : int
        +identification_number : uuid
        +date_of_creation : date
    }

    class Worker {
        +worker_id : int
        +type_worker : string
        +first_name : string
        +last_name : string
    }

    class Client {
        +client_id : int
        +first_name : string
        +last_name : string
        +address : string
        +postal : string
        +city : string
        +phone_number : string
    }

    class Account {
        +account_id : int
        +type_account : string
        +account_number : uuid
        +balance : decimal
        +date_of_creation : date
    }

    Agence "1" --> "1" Worker : dirige >
    Worker "1" --> "0..10" Client : conseille >
    Client "1" --> "0..1" Account : compte courant >
    Client "1" --> "0..1" Account : compte épargne >
```

---

# 3) User Stories minimales

## Gestion des clients

* ✔ **US1** – En tant que conseiller, je veux créer un client afin de l’ajouter dans mon portefeuille.
* ✔ **US2** – En tant que conseiller, je veux consulter les informations d’un client afin de visualiser son profil.
* ✔ **US3** – En tant que conseiller, je veux modifier un client afin de mettre à jour ses données.
* ~✔ **US4** – En tant que conseiller, je veux supprimer un client afin de retirer son dossier (et ses comptes associés).

## Gestion des comptes

* ✔ **US5** – Je veux créer un compte courant pour un client.
* ✔ **US6** – Je veux créer un compte épargne pour un client.
* ✔ **US7** – Je veux créditer/débiter un compte.

## Virements

* ✔ **US8** – Je veux effectuer un virement entre deux comptes de la banque (action loggée via AOP).

## Audit

* ✔ **US9** – En tant qu’auditeur, je veux générer un rapport des comptes créditeurs/débiteurs.

## Conseillers & agences

* ✔ **US10** – En tant que gérant, je veux assigner un client à un conseiller.
* x **US11** – En tant que direction, je veux assigner un gérant à une agence.

---

# 4) Technologies utilisées

| Composant                       | Version |
| ------------------------------- | ------- |
| **Java**                        | 21      |
| **Spring Boot**                 | 3.5.8   |
| **Spring Web (REST)**           | ✔       |
| **Spring Data JPA / Hibernate** | ✔       |
| **AOP (logging des virements)** | ✔       |
| **Maven**                       | ✔       |
| **MySQL / PostgreSQL**          | ✔       |

---

# 5) Installation & exécution

### 1. Cloner le projet

```bash
git clone https://github.com/<ton-repo>/projet_sk-peermahomed_nazif.git
cd projet_sk-peermahomed_nazif
```

### 2. Configurer la base de données (`src/main/resources/application.properties`)

```properties
spring.application.name=projet_sk-peermahomed_nazif

# Swagger / OpenAPI configuration (springdoc)
# UI: http://localhost:8080/swagger-ui/index.html
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui
springdoc.swagger-ui.operationsSorter=alpha
springdoc.swagger-ui.tagsSorter=alpha

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# Créer le schéma depuis les entités JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Activer la console H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

### 3. Lancer le projet

```bash
mvn spring-boot:run
```

---

# 6) Endpoints REST (exemple - non exhaustif)

* `GET /clients`
* `POST /clients`
* `PUT /clients/{id}`
* `DELETE /clients/{id}`
* `POST /virements`
* `GET /audit`
* `GET /accounts`
* `GET /workers`
* `GET /agences`

(La documentation Postman peut être ajoutée ultérieurement.)

---

# 7) Tests unitaires

* JUnit 5
* Mockito
* Spring Boot Test

---

# 8) Logging AOP

Toutes les opérations sensibles (comme les virements) sont automatiquement enregistrées dans :

```
logs/virement.log
```