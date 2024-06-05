package ru.patseev.recordsserver.repositoryies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.patseev.recordsserver.domain.StorageRecord;
import ru.patseev.recordsserver.domain.Tool;
import ru.patseev.recordsserver.domain.Worker;
import ru.patseev.recordsserver.domain.enums.Department;
import ru.patseev.recordsserver.domain.enums.ToolType;

import java.util.List;
import java.util.Optional;


public interface StorageRecordRepository extends JpaRepository<StorageRecord, Long> {

    List<StorageRecord> findAllByWorkerId(Long workerId);

    @Query("from StorageRecord S where S.worker.id =:workerId and S.tool.type =:toolType " +
            "and S.tool.code like %:toolCode% and S.amount > 0" )
    List<StorageRecord> findAllByWorkerIdWithToolTypeAndCode(Long workerId, ToolType toolType, String toolCode);

    @Query("from StorageRecord S where S.worker.lastName like %:workerLastname% and S.worker.department =:department")
    List<StorageRecord> findAllByWorkerLastname(Department department, String workerLastname);

    @Query("from StorageRecord S where S.worker =:worker and S.tool =:tool")
    Optional<StorageRecord> findStorageRecordByWorkerAndTool(Worker worker, Tool tool);

}
