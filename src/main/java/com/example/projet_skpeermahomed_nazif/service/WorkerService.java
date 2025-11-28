package com.example.projet_skpeermahomed_nazif.service;

import com.example.projet_skpeermahomed_nazif.dto.WorkerDTO;
import com.example.projet_skpeermahomed_nazif.entity.Worker;
import com.example.projet_skpeermahomed_nazif.exception.NotFoundException;
import com.example.projet_skpeermahomed_nazif.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerService {

    private final WorkerRepository workerRepository;

    public List<WorkerDTO> findAll() {
        return workerRepository.findAll().stream().map(this::toDTO).toList();
    }

    public WorkerDTO findById(Integer id) {
        return toDTO(getEntity(id));
    }

    public WorkerDTO create(WorkerDTO dto) {
        Worker w = new Worker();
        apply(dto, w);
        return toDTO(workerRepository.save(w));
    }

    public WorkerDTO update(Integer id, WorkerDTO dto) {
        Worker w = getEntity(id);
        apply(dto, w);
        return toDTO(workerRepository.save(w));
    }

    public void delete(Integer id) {
        if (!workerRepository.existsById(id)) {
            throw new NotFoundException("Worker not found: " + id);
        }
        workerRepository.deleteById(id);
    }

    private Worker getEntity(Integer id) {
        return workerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Worker not found: " + id));
    }

    private void apply(WorkerDTO dto, Worker w) {
        w.setTypeWorker(dto.typeWorker());
        w.setFirstName(dto.firstName());
        w.setLastName(dto.lastName());
    }

    private WorkerDTO toDTO(Worker w) {
        return new WorkerDTO(w.getId(), w.getTypeWorker(), w.getFirstName(), w.getLastName());
    }
}
