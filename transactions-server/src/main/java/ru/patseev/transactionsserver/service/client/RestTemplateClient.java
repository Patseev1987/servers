package ru.patseev.transactionsserver.service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import ru.patseev.transactionsserver.dto.StorageRecordDTO;
import ru.patseev.transactionsserver.dto.ToolDTO;
import ru.patseev.transactionsserver.dto.WorkerDTO;

import java.util.Objects;


@Component
@RequiredArgsConstructor
public class RestTemplateClient {
    private final RestTemplate restTemplate;

    //add record to storage-records-server
    public StorageRecordDTO addRecord(StorageRecordDTO record) {
        return restTemplate.postForObject(
                "http://my-gateway-server/records/add",
                record,
                StorageRecordDTO.class
        );
    }

    //get record by worker id and tool code
    public StorageRecordDTO getRecordByWorkerIdAndToolCode(Long workerId, String toolCode) {
        return restTemplate.getForObject(
                "http://my-gateway-server/records/record_by_worker_id_an_tool_code" +
                        "?workerId={workerId}&toolCode={toolCode}",
                StorageRecordDTO.class,
                workerId,
                toolCode
        );
    }

    //get worker by id
    public WorkerDTO getWorkerByWorkerId(Long workerId) {
        return restTemplate.getForObject(
                "http://my-gateway-server/records/workers/{workerId}",
                WorkerDTO.class,
                workerId
        );
    }

    //get tool by code
    public ToolDTO getToolByToolCode(String toolCode) {
        return restTemplate.getForObject(
                "http://my-gateway-server/records/tools/{toolCode}",
                ToolDTO.class,
                toolCode
        );
    }

    //update record
    public StorageRecordDTO updateRecord(StorageRecordDTO record) {
        RequestCallback callback = restTemplate.httpEntityCallback(record, StorageRecordDTO.class);
        ResponseExtractor<ResponseEntity<StorageRecordDTO>> extractor = restTemplate.responseEntityExtractor(StorageRecordDTO.class);
        ResponseEntity<StorageRecordDTO> restExchange = restTemplate.execute(
                "http://my-gateway-server/records/update",
                HttpMethod.PUT,
                callback,
                extractor);
        return Objects.requireNonNull(restExchange).getBody();
    }
}
