package ru.patseev.recordsserver.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patseev.recordsserver.domain.Place;
import ru.patseev.recordsserver.domain.StorageRecord;
import ru.patseev.recordsserver.domain.Tool;
import ru.patseev.recordsserver.domain.Worker;
import ru.patseev.recordsserver.domain.enums.Department;
import ru.patseev.recordsserver.domain.enums.ToolType;
import ru.patseev.recordsserver.domain.enums.WorkerType;
import ru.patseev.recordsserver.repositoryies.StorageRecordRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class StorageRecordService {
    private final StorageRecordRepository storageRecordRepository;
    private final ToolService toolService;
    private final WorkerService workerService;

    //get all records
    public List<StorageRecord> getAllRecords() {
        return storageRecordRepository.findAll();
    }

    //get records with param
    public List<StorageRecord> getStorageRecordsByWorkerIdWithParam(Long workerId, ToolType toolType, String toolCode) {
        return storageRecordRepository.findAllByWorkerIdWithToolTypeAndCode(workerId, toolType, toolCode);
    }

    //get records by worker lastname
    public List<StorageRecord> getAllStorageRecordsByWorkerLastName(Department department, String lastName) {
        return storageRecordRepository.findAllByWorkerLastname(department, lastName);
    }

    //add record
    public StorageRecord save(StorageRecord storageRecord) {
        return storageRecordRepository.save(storageRecord);
    }

    //get amount by worker id and tool code
    public Integer getAmountByWorkerIdAndToolCode(Long workerId, String toolCode) {

        var records = storageRecordRepository.findAllByWorkerId(workerId);
        records = records.stream()
                .filter(record -> record.getTool().getCode().equals(toolCode))
                .toList();
        if (records.isEmpty()) {
            return -1;
        } else {
            return records.get(0).getAmount();
        }
    }

    //get record by workerId and toolCode
    public Optional<StorageRecord> getStorageRecordByWorkerAndTool(Long workerId, String toolCode) {
        var worker = workerService.getWorkerById(workerId);
        var tool = toolService.getToolByCode(toolCode);
        return storageRecordRepository.findStorageRecordByWorkerAndTool(worker, tool);
    }

}
