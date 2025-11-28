package com.example.projet_skpeermahomed_nazif.repository;

import com.example.projet_skpeermahomed_nazif.entity.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    private Client build() {
        return Client.builder()
                .firstName("Alice")
                .lastName("Wonder")
                .address("1 rue A")
                .postal("75000")
                .city("Paris")
                .phoneNumber("0102030405")
                .build();
    }

    @Test
    void createUpdateDeleteClient() {
        // Create
        Client c = clientRepository.save(build());
        Assertions.assertNotNull(c.getId());

        // Update
        c.setCity("Lyon");
        Client updated = clientRepository.save(c);
        Assertions.assertEquals("Lyon", updated.getCity());

        // Delete
        Integer id = updated.getId();
        clientRepository.deleteById(id);
        Optional<Client> found = clientRepository.findById(id);
        Assertions.assertTrue(found.isEmpty());
    }
}
