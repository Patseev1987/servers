package ru.patseev.securityauthserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.patseev.securityauthserver.dto.StorageRecord;
import ru.patseev.securityauthserver.dto.Tool;
import ru.patseev.securityauthserver.dto.Worker;
import ru.patseev.securityauthserver.dto.enums.Department;
import ru.patseev.securityauthserver.dto.enums.ToolType;
import ru.patseev.securityauthserver.service.clients.RestTemplateRecordsClient;
import ru.patseev.securityauthserver.service.clients.RestTemplateWorkerClient;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RestTemplateRecordsClient rest;

    //get all records
    public List<StorageRecord> getRecords() {
        return rest.getStorageRecords();
    }

    //get records by worker id
    public List<StorageRecord> getRecordsByIdWorker(Long workerId, ToolType toolType, String toolCode) {
        return rest.getRecordsByIdWorker(workerId, toolType, toolCode);
    }

    //get tool amount with worker id and tool code
    public Integer getAmountByWorkerIdAndToolCode(Long workerId, String toolCode) {
        return rest.getAmountByWorkerIdAndToolCode(workerId, toolCode);
    }

    //get records by worker lastname and department
    public List<StorageRecord> getRecordsByWorkerLastName(String workerLastName, Department department) {
        return rest.getRecordsByWorkerLastName(workerLastName, department);
    }

    //add record
    public StorageRecord addRecord(StorageRecord record) {
        return rest.addRecord(record);
    }

    //get records by worker id and tool code
    public StorageRecord getRecordByWorkerIdAndToolCode(Long workerId, String toolCode) {
        return rest.getRecordByWorkerIdAndToolCode(workerId, toolCode);
    }

    //update record
    public StorageRecord updateRecord(StorageRecord record) {
        return rest.updateRecord(record);
    }
}
