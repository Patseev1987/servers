package ru.patseev.securityauthserver.service.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.patseev.securityauthserver.dto.StorageRecord;
import ru.patseev.securityauthserver.dto.enums.Department;
import ru.patseev.securityauthserver.dto.enums.ToolType;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RestTemplateRecordClient {
    private final RestTemplate restTemplate;


    public List<StorageRecord> getStorageRecords() {
        ResponseEntity<List<StorageRecord>> restExchange =
                restTemplate.exchange(
                        "http://my-gateway-server/records",
                        HttpMethod.GET,
                        null, new ParameterizedTypeReference<>() {
                        });
        return Objects.requireNonNull(restExchange.getBody());
    }

    public List<StorageRecord> getRecordsByIdWorker(Long workerId, ToolType toolType, String toolCode) {
        ResponseEntity<List<StorageRecord>> restExchange =
                restTemplate.exchange(
                        "http://my-gateway-server/records/workersId" +
                                "?workerId={workerId}&toolType={toolType}&toolCode={toolCode}",
                        HttpMethod.GET,
                        null, new ParameterizedTypeReference<>() {
                        },
                        workerId,
                        toolType,
                        toolCode);
        return restExchange.getBody();
    }

    public Integer getAmountByWorkerIdAndToolCode(Long workerId, String toolCode) {
        ResponseEntity<Integer> restExchange =
                restTemplate.exchange(
                        "http://my-gateway-server/records/amount?workerId={workerId}&toolCode={toolCode}",
                        HttpMethod.GET,
                        null, Integer.class, workerId, toolCode);
        return restExchange.getBody();
    }

    public List<StorageRecord> getRecordsByWorkerLastName(String workerLastName, Department department) {
        ResponseEntity<List<StorageRecord>> restExchange =
                restTemplate.exchange(
                        "http://my-gateway-server/records/worker_lastname" +
                                "?workerLastName={workerLastName}&department={department}",
                        HttpMethod.GET,
                        null, new ParameterizedTypeReference<>() {
                        }, workerLastName, department);
        return restExchange.getBody();
    }

    public StorageRecord addRecord(StorageRecord record) {
        ResponseEntity<StorageRecord> result = restTemplate.exchange(
                "http://my-gateway-server/records/add",
                HttpMethod.POST,
                null,
                StorageRecord.class, record
        );
        return result.getBody();
    }

    public StorageRecord getRecordByWorkerIdAndToolCode(Long workerId, String toolCode) {
        ResponseEntity<StorageRecord> result = restTemplate.exchange(
                "http://my-gateway-server/records/record_by_worker_id_an_tool_code" +
                        "?workerId={workerId}&toolCode={toolCode}",
                HttpMethod.GET,
                null,
                StorageRecord.class,
                workerId,
                toolCode
        );
        return result.getBody();
    }

    public StorageRecord updateRecord(StorageRecord record) {
        ResponseEntity<StorageRecord> result = restTemplate.exchange(
                "http://my-gateway-server/records/update",
                HttpMethod.PUT,
                null,
                StorageRecord.class, record
        );
        return result.getBody();
    }

}
