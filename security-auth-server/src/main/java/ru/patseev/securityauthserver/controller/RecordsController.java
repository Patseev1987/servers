package ru.patseev.securityauthserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.patseev.securityauthserver.dto.StorageRecord;
import ru.patseev.securityauthserver.dto.Tool;
import ru.patseev.securityauthserver.dto.Worker;
import ru.patseev.securityauthserver.dto.enums.Department;
import ru.patseev.securityauthserver.dto.enums.ToolType;
import ru.patseev.securityauthserver.service.RecordService;
import ru.patseev.securityauthserver.service.WorkerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/records")
public class RecordsController {
    private final RecordService service;


    @GetMapping
    //get all records
    public List<StorageRecord> getRecords() {
        return service.getRecords();
    }
    @GetMapping("/records_by_worker_id_and_tool_code_and_tool_type")
    //get records by worker id
    public List<StorageRecord> getRecordsByIdWorker(
            @RequestParam Long workerId,
            @RequestParam ToolType toolType,
            @RequestParam String toolCode) {
        return service.getRecordsByIdWorker(workerId, toolType, toolCode);
    }
    @GetMapping("/amount")
    //get tool amount with worker id and tool code
    public Integer getAmountByWorkerIdAndToolCode(
            @RequestParam Long workerId,
            @RequestParam String toolCode) {
        return service.getAmountByWorkerIdAndToolCode(workerId, toolCode);
    }
    @GetMapping("/records_worker_lastname")
    //get records by worker lastname and department
    public List<StorageRecord> getRecordsByWorkerLastName(
            @RequestParam String workerLastName,
            @RequestParam Department department) {
        return service.getRecordsByWorkerLastName(workerLastName, department);
    }
    @PostMapping("/add")
    //add record
    public StorageRecord addRecord(@RequestBody StorageRecord record) {
        return service.addRecord(record);
    }
    @GetMapping("/record_by_worker_id_and_tool_code")
    //get records by worker id and tool code
    public StorageRecord getRecordByWorkerIdAndToolCode(
           @RequestParam Long workerId,
           @RequestParam String toolCode) {
        return service.getRecordByWorkerIdAndToolCode(workerId, toolCode);
    }
    @PutMapping("/update")
    //update record
    public StorageRecord updateRecord(@RequestBody StorageRecord record) {
        return service.updateRecord(record);
    }


}
