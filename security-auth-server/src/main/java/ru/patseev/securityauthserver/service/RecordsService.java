package ru.patseev.securityauthserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patseev.securityauthserver.dto.StorageRecord;
import ru.patseev.securityauthserver.dto.Tool;
import ru.patseev.securityauthserver.dto.Worker;
import ru.patseev.securityauthserver.dto.enums.Department;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordsService {
    private final RestTemplateWorkerClient rest;

    public List<Worker> getWorkers(){
        return rest.getWorkers();
    }
    public List<Tool> getTools(){
        return rest.getTools();
    }
    public List<StorageRecord> getRecords(){
        return rest.getStorageRecords();
    }
    public Worker getStorageWorkerByDepartment(Department department){
        return rest.getStorageWorker(department);
    }
    public List<Worker> getWorkersByDepartment(Department department){
        return rest.getWorkersByDepartment(department);
    }
    public Worker getWorkerById(Long id){
        return rest.getWorkerById(id);
    }
    public Worker addWorker(Worker worker){
        return rest.addWorker(worker);
    }


}
