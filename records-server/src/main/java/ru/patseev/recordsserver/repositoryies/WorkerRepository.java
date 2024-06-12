package ru.patseev.recordsserver.repositoryies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.patseev.recordsserver.domain.Worker;
import ru.patseev.recordsserver.domain.enums.Department;

import java.util.List;
import java.util.Optional;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

    @Query("from Worker W where W.department =:department and W.type = 'STORAGE_WORKER'")
    Optional<Worker> findStorageWorkerByDepartment(Department department);

    @Query("from Worker W where W.department =:department and W.type = 'WORKER'")
    List<Worker> findAllWorkersByDepartment(Department department);
}
