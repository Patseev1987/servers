package ru.patseev.securityauthserver.service.clients;


import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import ru.patseev.securityauthserver.dto.Worker;
import ru.patseev.securityauthserver.dto.enums.Department;


import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RestTemplateWorkerClient {
    private final RestTemplate restTemplate;

    public List<Worker> getWorkers() {
        ResponseEntity<List<Worker>> restExchange =
                restTemplate.exchange(
                        "http://my-gateway-server/records/workers",
                        HttpMethod.GET,
                        null, new ParameterizedTypeReference<>() {
                        });
        return Objects.requireNonNull(restExchange.getBody());
    }

    public Worker getStorageWorker(Department department) {
        ResponseEntity<Worker> restExchange =
                restTemplate.exchange(
                        "http://my-gateway-server/records/workers/storage_worker_by_department?department={department}",
                        HttpMethod.GET,
                        null, Worker.class, department);
        return restExchange.getBody();
    }

    public List<Worker> getWorkersByDepartment(Department department) {
        ResponseEntity<List<Worker>> restExchange =
                restTemplate.exchange(
                        "http://my-gateway-server/records/workers/workers_by_department?department={department}",
                        HttpMethod.GET,
                        null, new ParameterizedTypeReference<>() {
                        }, department);
        return Objects.requireNonNull(restExchange.getBody());
    }

    public Worker getWorkerById(Long workerId) {
        ResponseEntity<Worker> restExchange =
                restTemplate.exchange(
                        "http://my-gateway-server/records/workers/{workerId}",
                        HttpMethod.GET,
                        null, Worker.class, workerId);
        return restExchange.getBody();
    }

    public Worker addWorker(Worker worker) {
       return restTemplate.postForObject(
                "http://my-gateway-server/records/workers/add",
                worker,
                Worker.class
        );
    }

    public Worker updateWorker(Worker worker) {
        RequestCallback callback = restTemplate.httpEntityCallback(worker, Worker.class);
        ResponseExtractor<ResponseEntity<Worker>> extractor = restTemplate.responseEntityExtractor(Worker.class);
        ResponseEntity<Worker> result = restTemplate.execute(
                "http://my-gateway-server/records/workers/update",
                HttpMethod.PUT,
                callback,
                extractor
        );
        return Objects.requireNonNull(result).getBody();
    }
}
