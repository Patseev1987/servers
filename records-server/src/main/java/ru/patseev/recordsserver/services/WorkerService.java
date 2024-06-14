package ru.patseev.recordsserver.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patseev.recordsserver.domain.Worker;
import ru.patseev.recordsserver.domain.enums.Department;
import ru.patseev.recordsserver.domain.enums.WorkerType;
import ru.patseev.recordsserver.repositoryies.WorkerRepository;
import ru.patseev.recordsserver.services.client.RestTemplateClient;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerService {

    private final WorkerRepository workerRepository;
    private final RestTemplateClient restTemplateClient;


    //add worker
    public Worker create(Worker worker) {
        var newWorker = workerRepository.save(worker);
        restTemplateClient.addWorker(newWorker);
        return newWorker;
    }

    //update worker
    public Worker update(Worker worker) {
        var newWorker = workerRepository.save(worker);
        restTemplateClient.updateWorker(newWorker);
        return newWorker;
    }

    //get all workers
    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    //get storage worker by department
    public Worker getStorageWorkerByDepartment(Department department) {
        return workerRepository.findStorageWorkerByDepartment(department).orElseThrow();
    }

    //get workers by department
    public List<Worker> getWorkersByDepartment(Department department) {
        return workerRepository.findAllWorkersByDepartment(department);
    }

    //get worker by id
    public Worker getWorkerById(Long workerId) {
        return workerRepository.findById(workerId).orElse(null);
    }
}
