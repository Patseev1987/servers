package ru.patseev.securityauthserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.patseev.securityauthserver.domain.Worker;
import ru.patseev.securityauthserver.service.utils.WorkerList;


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
}
