package ru.patseev.recordsserver.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patseev.recordsserver.domain.Worker;
import ru.patseev.recordsserver.domain.enums.Department;
import ru.patseev.recordsserver.domain.enums.WorkerType;
import ru.patseev.recordsserver.repositoryies.WorkerRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerService {
    private final WorkerRepository workerRepository;

    public Worker create(Worker worker) {
        return workerRepository.save(worker);
    }



    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    private Worker wrongAnswerWorker(){
        Worker worker = new Worker();
        worker.setPassword("");
        worker.setLogin("");
        worker.setId(-1L);
        worker.setFirstName("");
        worker.setLastName("");
        worker.setJoinDate(LocalDate.now());
        worker.setDepartment(Department.DEPARTMENT_19);
        worker.setType(WorkerType.WORKER);
        worker.setPatronymic("");
        return worker;
    }

    public Worker getStorageWorkerByDepartment(Department department) {
        return workerRepository.findStorageWorkerByDepartment(department).orElseThrow();
    }


    public List<Worker> getWorkersByDepartment(Department department) {
        return workerRepository.findAllWorkersByDepartment(department);
    }
}
