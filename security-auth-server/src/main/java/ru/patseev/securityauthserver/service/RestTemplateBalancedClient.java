package ru.patseev.securityauthserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.patseev.securityauthserver.dto.StorageRecord;
import ru.patseev.securityauthserver.dto.Tool;
import ru.patseev.securityauthserver.dto.Worker;


import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RestTemplateBalancedClient {
    private final RestTemplate restTemplate;

    public List<Worker> getWorkers(){
        ResponseEntity<List<Worker>> restExchange =
                restTemplate.exchange(
                        "http://my-gateway-server/records/workers",
                        HttpMethod.GET,
                        null, new ParameterizedTypeReference <>(){});
        return Objects.requireNonNull(restExchange.getBody());
    }

    public List<Tool> getTools (){
        ResponseEntity<List<Tool>> restExchange =
                restTemplate.exchange(
                        "http://my-gateway-server/records/tools",
                        HttpMethod.GET,
                        null, new ParameterizedTypeReference <>(){});
        return Objects.requireNonNull(restExchange.getBody());
    }

    public List<StorageRecord> getStorageRecords(){
        ResponseEntity<List<StorageRecord>> restExchange =
                restTemplate.exchange(
                        "http://my-gateway-server/records",
                        HttpMethod.GET,
                        null, new ParameterizedTypeReference <>(){});
        return Objects.requireNonNull(restExchange.getBody());
    }




//    //get records by worker
//    @GetMapping("/workerId")
//    public List<StorageRecord> getRecordsByIdWorker(
//            @RequestParam(name = "workerId") Long workerId,
//            @RequestParam(name = "toolType") ToolType toolType,
//            @RequestParam(name = "toolCode" , defaultValue = "") String toolCode
//    ) {
//        return storageRecordService.getStorageRecordsByWorkerIdWithParam(workerId, toolType, toolCode);
//    }
//    //get amount by worker id and tool code
//    @GetMapping("/amount")
//    public Integer getAmountByWorkerIdAndToolCode(
//            @RequestParam(name = "workerId") Long workerId,
//            @RequestParam(name = "toolCode") String toolCode
//    ) {
//        return storageRecordService.getAmountByWorkerIdAndToolCode(workerId,toolCode);
//    }
//    //get records
//    @GetMapping
//    public List<StorageRecord> getAllRecords() {
//        return   storageRecordService.getAllRecords();
//    }
//
//    //get records by worker's lastname
//    @GetMapping("/worker_lastname")
//    public List<StorageRecord> getRecordsByWorkerLastName(
//            @RequestParam(name = "workerLastName") String workerLastName,
//            @RequestParam(name = "department") Department department
//    ){
//        return   storageRecordService.getAllStorageRecordsByWorkerLastName(
//                department,
//                workerLastName
//        );
//    }
//    //add record
//    @PostMapping("/add")
//    public StorageRecord addRecord(@RequestBody StorageRecord record) {
//        return   storageRecordService.save(record);
//    }
//
//    //get record by worker id and tool code
//    @GetMapping("/record_by_worker_id_an_tool_code")
//    public StorageRecord getRecordByWorkerIdAndToolCode(
//            @RequestParam(name = "workerId") Long workerId,
//            @RequestParam(name = "toolCode") String toolCode
//    ){
//        return storageRecordService.getStorageRecordByWorkerAndTool(workerId, toolCode)
//                .orElse(StorageRecord.builder()
//                        .id(-1L)
//                        .build());
//    }
//
//    //update record
//    @PutMapping("/update")
//    public StorageRecord updateRecord(@RequestBody StorageRecord record) {
//        return storageRecordService.save(record);
//    }
}
