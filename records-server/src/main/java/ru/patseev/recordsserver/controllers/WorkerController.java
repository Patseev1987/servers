package ru.patseev.recordsserver.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.patseev.recordsserver.domain.enums.Department;
import ru.patseev.recordsserver.domain.Worker;
import ru.patseev.recordsserver.services.WorkerService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WorkerController {
    private final WorkerService workerService;

    @GetMapping("/workers")
    public List<Worker> getAllWorkers() {
        return workerService.getAllWorkers();
    }

    @GetMapping("/storage_worker_by_department")
    public Worker getStorageWorkerByDepartment(@RequestParam(name = "department") Department department){
        return workerService.getStorageWorkerByDepartment(department);
    }

    @GetMapping("/workers_by_department")
    public List<Worker> getWorkersByDepartment(@RequestParam(name = "department") Department department){
        return workerService.getWorkersByDepartment(department);
    }

}
