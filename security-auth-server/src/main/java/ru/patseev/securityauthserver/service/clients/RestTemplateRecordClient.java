package ru.patseev.securityauthserver.service.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import ru.patseev.securityauthserver.dto.StorageRecord;
import ru.patseev.securityauthserver.dto.Tool;
import ru.patseev.securityauthserver.dto.enums.Department;
import ru.patseev.securityauthserver.dto.enums.ToolType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        Map<String, Object> values = new HashMap<>();
        values.put("workerId", workerId);
        values.put("toolType", toolType);
        values.put("toolCode", toolCode);

        ResponseEntity<List<StorageRecord>> restExchange =
                restTemplate.exchange(
                        "http://my-gateway-server/records/workerId" +
                                "?workerId={workerId}&toolType={toolType}&toolCode={toolCode}",
                        HttpMethod.GET,
                        null, new ParameterizedTypeReference<>() {
                        }, workerId, toolType, toolCode);
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
        StorageRecord result = restTemplate.postForObject(
                "http://my-gateway-server/records/add",
                record,
                StorageRecord.class
        );
        return result;
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
        RequestCallback callback = restTemplate.httpEntityCallback(record, StorageRecord.class);
        ResponseExtractor<ResponseEntity<StorageRecord>> extractor = restTemplate.responseEntityExtractor(StorageRecord.class);
        ResponseEntity<StorageRecord> restExchange = restTemplate.execute(
                "http://my-gateway-server/records/update",
                HttpMethod.PUT,
                callback,
                extractor);
        return Objects.requireNonNull(restExchange).getBody();
    }

}
