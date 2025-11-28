package com.example.projet_skpeermahomed_nazif.repository;

import com.example.projet_skpeermahomed_nazif.entity.Worker;
import com.example.projet_skpeermahomed_nazif.entity.enums.TypeWorker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class WorkerRepositoryTest {

    @Autowired
    private WorkerRepository workerRepository;

    private Worker build() {
        return Worker.builder()
                .typeWorker(TypeWorker.CONSEILLE)
                .firstName("John")
                .lastName("Doe")
                .build();
    }

    @Test
    void createUpdateDeleteWorker() {
        // Create
        Worker w = workerRepository.save(build());
        Assertions.assertNotNull(w.getId());

        // Update
        w.setLastName("Smith");
        Worker updated = workerRepository.save(w);
        Assertions.assertEquals("Smith", updated.getLastName());

        // Delete
        Integer id = updated.getId();
        workerRepository.deleteById(id);
        Optional<Worker> found = workerRepository.findById(id);
        Assertions.assertTrue(found.isEmpty());
    }
}
