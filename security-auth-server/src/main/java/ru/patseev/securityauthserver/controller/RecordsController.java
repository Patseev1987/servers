package ru.patseev.securityauthserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.patseev.securityauthserver.dto.StorageRecord;
import ru.patseev.securityauthserver.dto.Tool;
import ru.patseev.securityauthserver.dto.Worker;
import ru.patseev.securityauthserver.dto.enums.Department;
import ru.patseev.securityauthserver.service.RecordsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecordsController {
    private final RecordsService service;

    @GetMapping("/workers")
    public List<Worker> getWorkers() {
        return service.getWorkers();
    }

    @GetMapping("/tools")
    public List<Tool> getTools() {
        return service.getTools();
    }

    @GetMapping("/records")
    public List<StorageRecord> getRecords() {
        return service.getRecords();
    }

    @GetMapping("/storage_worker_by_department")
    public Worker getStorageWorkerByDepartment(
            @RequestParam(name = "department") Department department
    ) {
        return service.getStorageWorkerByDepartment(department);
    }

    @GetMapping("/workers_by_department")
    public List<Worker> getWorkers(
            @RequestParam(name = "department") Department department
    ) {
        return service.getWorkersByDepartment(department);
    }

    @GetMapping("/workers/{id}")
    public Worker getWorkerById(@PathVariable Long id) {
        return service.getWorkerById(id);
    }

    @PostMapping("/workers/add")
    public Worker addWorker(@RequestBody Worker worker) {
        return service.addWorker(worker);
    }

    @PutMapping("/workers/update")
    public Worker wupdateWorker(@RequestBody Worker worker) {
        return service.addWorker(worker);
    }
}
