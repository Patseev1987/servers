package ru.patseev.recordsserver.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.patseev.recordsserver.domain.enums.Department;
import ru.patseev.recordsserver.domain.Worker;
import ru.patseev.recordsserver.services.WorkerService;

import java.util.List;

@RestController
@RequestMapping("/records/workers")
@RequiredArgsConstructor
public class WorkerController {
    private final WorkerService workerService;
    //get workers
    @GetMapping
    public List<Worker> getAllWorkers() {
        return workerService.getAllWorkers();
    }
    //get storage worker by department
    @GetMapping("/storage_worker_by_department")
    public Worker getStorageWorkerByDepartment(@RequestParam(name = "department") Department department){
        return workerService.getStorageWorkerByDepartment(department);
    }
    //get workers by department
    @GetMapping("/workers_by_department")
    public List<Worker> getWorkersByDepartment(@RequestParam(name = "department") Department department){
        return workerService.getWorkersByDepartment(department);
    }
    //get worker by id
    @GetMapping("/{id}")
    public Worker getWorker(@PathVariable Long id) {
        return workerService.getWorkerById(id);
    }
    //create worker
    @PostMapping("/add")
    public Worker createWorker(@RequestBody Worker worker) {
        return workerService.create(worker);
    }
    //update worker
    @PutMapping("/update")
    public Worker updateWorker(@RequestBody Worker worker) {
        return createWorker(worker);
    }
    //delete worker by id
    @DeleteMapping("/delete/{id}")
    public void deleteWorker(@PathVariable Long id) {
        workerService.delete(id);
    }
}
