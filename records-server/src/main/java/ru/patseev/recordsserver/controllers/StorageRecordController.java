package ru.patseev.recordsserver.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.patseev.recordsserver.domain.enums.Department;
import ru.patseev.recordsserver.domain.StorageRecord;
import ru.patseev.recordsserver.domain.enums.ToolType;
import ru.patseev.recordsserver.services.StorageRecordService;

import java.util.List;

@RestController
@RequestMapping("/records")
@RequiredArgsConstructor
public class StorageRecordController {
    private final StorageRecordService storageRecordService;
    //get records by worker
    @GetMapping ("/workerId")
    public List<StorageRecord> getRecordsByIdWorker(
            @RequestParam(name = "workerId") Long workerId,
            @RequestParam(name = "toolType") ToolType toolType,
            @RequestParam(name = "toolCode" , defaultValue = "") String toolCode
    ) {
        return storageRecordService.getStorageRecordsByWorkerIdWithParam(workerId, toolType, toolCode);
    }
    //get amount by worker id and tool code
    @GetMapping("/amount")
    public Integer getAmountByWorkerIdAndToolCode(
            @RequestParam(name = "workerId") Long workerId,
            @RequestParam(name = "toolCode") String toolCode
    ) {
        return storageRecordService.getAmountByWorkerIdAndToolCode(workerId,toolCode);
    }
    //get records
    @GetMapping
    public List<StorageRecord> getAllRecords() {
      return   storageRecordService.getAllRecords();
    }

    //get records by worker's lastname
    @GetMapping("/worker_lastname")
    public List<StorageRecord> getRecordsByWorkerLastName(
            @RequestParam(name = "workerLastName") String workerLastName,
            @RequestParam(name = "department") Department department
    ){
      return   storageRecordService.getAllStorageRecordsByWorkerLastName(
                department,
                workerLastName
        );
    }
    //add record
    @PostMapping("/add")
    public StorageRecord addRecord(@RequestBody StorageRecord record) {
      return   storageRecordService.save(record);
    }

    //get record by worker id and tool code
    @GetMapping("/record_by_worker_id_an_tool_code")
    public StorageRecord getRecordByWorkerIdAndToolCode(
            @RequestParam(name = "workerId") Long workerId,
            @RequestParam(name = "toolCode") String toolCode
    ){
        return storageRecordService.getStorageRecordByWorkerAndTool(workerId, toolCode)
                .orElse(StorageRecord.builder()
                        .id(-1L)
                        .build());
    }

    //update record
    @PutMapping("/update")
    public StorageRecord updateRecord(@RequestBody StorageRecord record) {
        return storageRecordService.save(record);
    }
}
