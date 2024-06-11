package ru.patseev.recordsserver.services.client;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import ru.patseev.recordsserver.domain.Tool;
import ru.patseev.recordsserver.domain.Worker;
import ru.patseev.recordsserver.dto.ToolDTO;
import ru.patseev.recordsserver.dto.WorkerDTO;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RestTemplateClient {
    private final RestTemplate restTemplate;

    //add tool from transaction-server
    public ToolDTO addTool(Tool tool) {
        var toolDTO = ToolDTO.builder()
                .name(tool.getName())
                .code(tool.getCode())
                .build();
        return restTemplate.postForObject(
                "http://my-gateway-server/transactions/tools/add",
                toolDTO,
                ToolDTO.class
        );
    }

    //update tool from transaction-server
    public void updateTool(Tool tool) {
        var toolDTO = ToolDTO.builder()
                .name(tool.getName())
                .code(tool.getCode())
                .build();
        restTemplate.put("http://my-gateway-server/transactions/tools/update", toolDTO);
    }

    //add worker from transaction-server
    public WorkerDTO addWorker(Worker worker) {
        var workerDTO = WorkerDTO.builder()
                .firstName(worker.getFirstName())
                .lastName(worker.getLastName())
                .department(worker.getDepartment())
                .id(worker.getId())
                .build();
        return restTemplate.postForObject(
                "http://my-gateway-server/transactions/workers/add",
                workerDTO,
                WorkerDTO.class
        );
    }

    //update worker from transaction-server
    public void updateWorker(Worker worker) {
        var workerDTO = WorkerDTO.builder()
                .firstName(worker.getFirstName())
                .lastName(worker.getLastName())
                .department(worker.getDepartment())
                .id(worker.getId())
                .build();
        restTemplate.put("http://my-gateway-server/transactions/workers/update", workerDTO);
    }
}
