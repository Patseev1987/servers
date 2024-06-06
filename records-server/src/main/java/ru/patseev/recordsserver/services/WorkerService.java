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

    {
      //  initData();
    }

    //add worker
    public Worker create(Worker worker) {
        return workerRepository.save(worker);
    }
    //delete worker by id
    public void delete(Long workerId) {
        workerRepository.deleteById(workerId);
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



    private void initData(){
        new Thread( () -> {
            var worker1 = Worker.builder()
                    .id(1L)
                    .firstName("ivan")
                    .lastName("ivanov")
                    .login("rrr")
                    .type(WorkerType.WORKER)
                    .department(Department.DEPARTMENT_19)
                    .joinDate(LocalDate.now())
                    .patronymic("ivanovich")
                    .build();

            var worker2 = Worker.builder()
                    .id(2L)
                    .firstName("sergey")
                    .lastName("sergeev")
                    .login("sss")
                    .type(WorkerType.WORKER)
                    .department(Department.DEPARTMENT_19)
                    .joinDate(LocalDate.now())
                    .patronymic("sergeevich")
                    .build();

            var worker3 = Worker.builder()
                    .id(3L)
                    .firstName("kladovaya")
                    .lastName("kladovaya")
                    .login("kkk")
                    .type(WorkerType.STORAGE_WORKER)
                    .department(Department.DEPARTMENT_19)
                    .joinDate(LocalDate.now())
                    .patronymic("kladovaya")
                    .build();

            var worker4 = Worker.builder()
                    .id(4L)
                    .firstName("Dimon")
                    .lastName("Dimonov")
                    .login("ddd")
                    .type(WorkerType.WORKER)
                    .department(Department.DEPARTMENT_19)
                    .joinDate(LocalDate.now())
                    .patronymic("D")
                    .build();
            var workers = List.of(worker1, worker2, worker3, worker4);
            try {
                Thread.sleep(500L);
                workerRepository.saveAll(workers);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }).start();
    }
}
