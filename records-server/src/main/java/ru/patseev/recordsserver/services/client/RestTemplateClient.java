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
import ru.patseev.recordsserver.dto.ToolDTO;

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
    public ToolDTO updateTool(Tool tool) {
        var toolDTO = ToolDTO.builder()
                .name(tool.getName())
                .code(tool.getCode())
                .build();
        RequestCallback callback = restTemplate.httpEntityCallback(toolDTO, ToolDTO.class);
        ResponseExtractor<ResponseEntity<ToolDTO>> extractor = restTemplate.responseEntityExtractor(Tool.class);
        ResponseEntity<ToolDTO> restExchange = restTemplate.execute(
                "http://my-gateway-server/records/tools/update",
                HttpMethod.PUT,
                callback,
                extractor);
        return Objects.requireNonNull(restExchange).getBody();
    }
}
