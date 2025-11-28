# projet_sk-peermahomed_nazif

# UML – Architecture du projet SimpleCash

Ce diagramme représente l’architecture en couches du projet Spring Boot :  
**Controller → Service → Repository → Entity**

```mermaid
classDiagram
    %% PACKAGES
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

    %% RELATIONS ENTRE COUCHES
    controller --> service : "appelle"
    service --> repository : "utilise"
    repository --> entity : "manipule"
```
---

# **2) Fichier : `uml-domain-model.md`**

```markdown
# UML – Vue Simplifiée des Classes Métier

Ce diagramme représente uniquement les **entités métier** et leurs **relations**.
```
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

    %% RELATIONS MÉTIER
    Agence "1" --> "1" Worker : dirige >
    Worker "1" --> "0..10" Client : conseille >
    Client "1" --> "0..1" Account : compte courant >
    Client "1" --> "0..1" Account : compte épargne >
```