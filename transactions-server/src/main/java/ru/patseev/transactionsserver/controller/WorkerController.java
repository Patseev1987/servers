package ru.patseev.transactionsserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.patseev.transactionsserver.domain.Worker;
import ru.patseev.transactionsserver.service.WorkerService;

import java.util.List;

@RestController
@RequestMapping("/transactions/workers")
@RequiredArgsConstructor
public class WorkerController {
    private final WorkerService workerService;
    private final Long ERROR_ID = -1L;
    //get workers
    @GetMapping
    public List<Worker> getAllWorkers() {
        return workerService.getAllWorkers();
    }
    //add worker
    @PostMapping("/add")
    public Worker addWorker(@RequestBody Worker worker) {
        return workerService.createWorker(worker);
    }
    //update worker
    @PutMapping("/update")
    public Worker updateWorker(@RequestBody Worker worker) {
        return workerService.createWorker(worker);
    }
    //get worker by id
    @GetMapping("/{id}")
    public Worker getWorkerById(@PathVariable("id") Long id) {
        return workerService.getWorkerById(id).orElse(Worker.builder().id(ERROR_ID).build());
    }
}
