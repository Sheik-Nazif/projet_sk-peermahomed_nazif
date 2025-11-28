package com.example.projet_skpeermahomed_nazif.service;

import com.example.projet_skpeermahomed_nazif.dto.ClientDTO;
import com.example.projet_skpeermahomed_nazif.entity.Account;
import com.example.projet_skpeermahomed_nazif.entity.Client;
import com.example.projet_skpeermahomed_nazif.entity.Worker;
import com.example.projet_skpeermahomed_nazif.exception.NotFoundException;
import com.example.projet_skpeermahomed_nazif.repository.AccountRepository;
import com.example.projet_skpeermahomed_nazif.repository.ClientRepository;
import com.example.projet_skpeermahomed_nazif.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final WorkerRepository workerRepository;
    private final AccountRepository accountRepository;

    public List<ClientDTO> findAll() {
        return clientRepository.findAll().stream().map(this::toDTO).toList();
    }

    public ClientDTO findById(Integer id) {
        return toDTO(getEntity(id));
    }

    public ClientDTO create(ClientDTO dto) {
        Client client = new Client();
        apply(dto, client);
        return toDTO(clientRepository.save(client));
    }

    public ClientDTO update(Integer id, ClientDTO dto) {
        Client client = getEntity(id);
        apply(dto, client);
        return toDTO(clientRepository.save(client));
    }

    public void delete(Integer id) {
        if (!clientRepository.existsById(id)) {
            throw new NotFoundException("Client not found: " + id);
        }
        clientRepository.deleteById(id);
    }

    private Client getEntity(Integer id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found: " + id));
    }

    private void apply(ClientDTO dto, Client client) {
        client.setFirstName(dto.firstName());
        client.setLastName(dto.lastName());
        client.setAddress(dto.address());
        client.setPostal(dto.postal());
        client.setCity(dto.city());
        client.setPhoneNumber(dto.phoneNumber());

        if (dto.workerId() != null) {
            Worker worker = workerRepository.findById(dto.workerId())
                    .orElseThrow(() -> new NotFoundException("Worker not found: " + dto.workerId()));
            client.setWorker(worker);
        } else {
            client.setWorker(null);
        }

        if (dto.courantAccountId() != null) {
            Account a = accountRepository.findById(dto.courantAccountId())
                    .orElseThrow(() -> new NotFoundException("Account not found: " + dto.courantAccountId()));
            client.setCourantAccount(a);
        } else {
            client.setCourantAccount(null);
        }

        if (dto.epargnAccountId() != null) {
            Account a = accountRepository.findById(dto.epargnAccountId())
                    .orElseThrow(() -> new NotFoundException("Account not found: " + dto.epargnAccountId()));
            client.setEpargnAccount(a);
        } else {
            client.setEpargnAccount(null);
        }
    }

    private ClientDTO toDTO(Client c) {
        Integer workerId = c.getWorker() != null ? c.getWorker().getId() : null;
        Integer courantId = c.getCourantAccount() != null ? c.getCourantAccount().getId() : null;
        Integer epargnId = c.getEpargnAccount() != null ? c.getEpargnAccount().getId() : null;
        return new ClientDTO(
                c.getId(),
                c.getFirstName(),
                c.getLastName(),
                c.getAddress(),
                c.getPostal(),
                c.getCity(),
                c.getPhoneNumber(),
                workerId,
                courantId,
                epargnId
        );
    }
}
