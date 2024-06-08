package ru.patseev.securityauthserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.patseev.securityauthserver.domain.Worker;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RestTemplateBalancedClient rest;

    public List<Worker> getWorkers(){
        return rest.getWorkers();
    }
}
