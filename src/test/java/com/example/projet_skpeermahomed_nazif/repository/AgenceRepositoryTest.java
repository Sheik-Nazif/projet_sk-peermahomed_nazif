package com.example.projet_skpeermahomed_nazif.repository;

import com.example.projet_skpeermahomed_nazif.entity.Agence;
import com.example.projet_skpeermahomed_nazif.entity.Worker;
import com.example.projet_skpeermahomed_nazif.entity.enums.TypeWorker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class AgenceRepositoryTest {

    @Autowired
    private AgenceRepository agenceRepository;

    @Autowired
    private WorkerRepository workerRepository;

    private Worker persistWorker() {
        Worker w = Worker.builder()
                .typeWorker(TypeWorker.GERANT)
                .firstName("Jane")
                .lastName("Doe")
                .build();
        return workerRepository.save(w);
    }

    @Test
    void createUpdateDeleteAgence() {
        Worker w = persistWorker();

        // Create agence
        Agence a = Agence.builder()
                .worker(w)
                .identificationNumber(UUID.randomUUID())
                .dateOfCreation(LocalDate.now())
                .build();
        a = agenceRepository.save(a);
        Assertions.assertNotNull(a.getId());

        // Update (change worker)
        Worker w2 = Worker.builder()
                .typeWorker(TypeWorker.CONSEILLE)
                .firstName("Paul")
                .lastName("Martin")
                .build();
        w2 = workerRepository.save(w2);
        a.setWorker(w2);
        Agence updated = agenceRepository.save(a);
        Assertions.assertEquals(w2.getId(), updated.getWorker().getId());

        // Delete
        Integer id = updated.getId();
        agenceRepository.deleteById(id);
        Optional<Agence> found = agenceRepository.findById(id);
        Assertions.assertTrue(found.isEmpty());
    }
}
