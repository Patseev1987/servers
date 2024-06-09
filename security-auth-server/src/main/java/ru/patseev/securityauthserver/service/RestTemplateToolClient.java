package ru.patseev.securityauthserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.patseev.securityauthserver.dto.StorageRecord;
import ru.patseev.securityauthserver.dto.Tool;
import ru.patseev.securityauthserver.dto.Worker;
import ru.patseev.securityauthserver.dto.enums.Department;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RestTemplateToolClient {
    private final RestTemplate restTemplate;

    public List<Tool> getTools (){
        ResponseEntity<List<Tool>> restExchange =
                restTemplate.exchange(
                        "http://my-gateway-server/records/tools",
                        HttpMethod.GET,
                        null, new ParameterizedTypeReference <>(){});
        return Objects.requireNonNull(restExchange.getBody());
    }



}
