package ru.patseev.transactionsserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.patseev.transactionsserver.domain.Department;
import ru.patseev.transactionsserver.domain.ToolType;
import ru.patseev.transactionsserver.service.StorageRecordService;
import ru.patseev.transactionsserver.domain.StorageRecord;

import java.util.List;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class StorageRecordController {
    private final StorageRecordService storageRecordService;

    @GetMapping ("/workerId")
    public List<StorageRecord> getToolsByIdWorker(
            @RequestParam(name = "workerId") Long workerId,
            @RequestParam(name = "toolType") ToolType toolType,
            @RequestParam(name = "toolCode" , defaultValue = "") String toolCode
    ) {
        return storageRecordService.getStorageRecordsByWorkerIdWithParam(workerId, toolType, toolCode);
    }

    @GetMapping("/amount")
    public Integer getAmountByWorkerIdAndToolCode(
            @RequestParam(name = "workerId") Long workerId,
            @RequestParam(name = "toolCode") String toolCode
    ) {
        return storageRecordService.getAmountByWorkerIdAndToolCode(workerId,toolCode);
    }

    @GetMapping ("")
    public List<StorageRecord> getAllRecords() {
      return   storageRecordService.getAllRecords();
    }


    @GetMapping("/workers")
    public List<StorageRecord> getRecordsByWorkerLastName(
            @RequestParam(name = "workerLastName") String workerLastName,
            @RequestParam(name = "department") Department department
    ){
      return   storageRecordService.getAllStorageRecordsByWorkerLastName(
                department,
                workerLastName
        );
    }
}
