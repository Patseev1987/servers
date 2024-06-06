package ru.patseev.transactionsserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patseev.transactionsserver.domain.Worker;
import ru.patseev.transactionsserver.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkerService {
    private final WorkerRepository workerRepository;

    //get all workers
    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }
    //get worker by id
    public Optional<Worker> getWorkerById(Long id) {
        return workerRepository.findById(id);
    }
    //add worker
    public Worker createWorker(Worker worker) {
        return workerRepository.save(worker);
    }
}
