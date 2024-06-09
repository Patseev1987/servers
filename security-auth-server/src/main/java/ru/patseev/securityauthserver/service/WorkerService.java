package ru.patseev.securityauthserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patseev.securityauthserver.dto.StorageRecord;
import ru.patseev.securityauthserver.dto.Tool;
import ru.patseev.securityauthserver.dto.Worker;
import ru.patseev.securityauthserver.dto.enums.Department;
import ru.patseev.securityauthserver.service.clients.RestTemplateWorkerClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerService {
    private final RestTemplateWorkerClient rest;

    //get all workers
    public List<Worker> getWorkers() {
        return rest.getWorkers();
    }

    //get storage worker by department
    public Worker getStorageWorkerByDepartment(Department department) {
        return rest.getStorageWorker(department);
    }

    //get workers by department
    public List<Worker> getWorkersByDepartment(Department department) {
        return rest.getWorkersByDepartment(department);
    }

    //get worker by id
    public Worker getWorkerById(Long id) {
        return rest.getWorkerById(id);
    }

    //add worker
    public Worker addWorker(Worker worker) {
        return rest.addWorker(worker);
    }

    //update worker
    public Worker updateWorker(Worker worker) {
        return rest.updateWorker(worker);
    }

}
