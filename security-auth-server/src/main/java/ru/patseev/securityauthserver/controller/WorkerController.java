package ru.patseev.securityauthserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.patseev.securityauthserver.domain.Worker;
import ru.patseev.securityauthserver.service.RequestService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkerController {
    private final RequestService service;

    @GetMapping("/workers")
    public List<Worker> getWorkers() {
        return service.getWorkers();
    }
}
