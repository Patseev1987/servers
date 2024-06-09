package ru.patseev.securityauthserver.service.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import ru.patseev.securityauthserver.dto.StorageRecord;
import ru.patseev.securityauthserver.dto.Transaction;
import ru.patseev.securityauthserver.dto.enums.Department;
import ru.patseev.securityauthserver.dto.enums.ToolType;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RestTemplateTransactionClient {
    private final RestTemplate restTemplate;

    //get all transactions
    public List<Transaction> getStorageTransactions() {
        ResponseEntity<List<Transaction>> restExchange =
                restTemplate.exchange(
                        "http://my-gateway-server/transactions/transactions",
                        HttpMethod.GET,
                        null, new ParameterizedTypeReference<>() {
                        });
        return restExchange.getBody();
    }

    //create transaction
    public Transaction createTransaction(Transaction transaction) {
        ResponseEntity<Transaction> restExchange =
                restTemplate.exchange(
                        "http://my-gateway-server/transactions/transactions/create",
                        HttpMethod.POST,
                        null, new ParameterizedTypeReference<>() {
                        }, transaction);
        return restExchange.getBody();
    }

    //get transactions by worker id
    public List<Transaction> getTransactionsByWorkerId(Long workerId) {
        ResponseEntity<List<Transaction>> restExchange =
                restTemplate.exchange(
                        "http://my-gateway-server/transactions/transactions/worker?workerId={workerId}",
                        HttpMethod.GET,
                        null, new ParameterizedTypeReference<>() {
                        }, workerId);
        return restExchange.getBody();
    }

    //get transaction with another departments
    public List<Transaction> getTransactionsWitheAnotherDepartment(Department anotherDepartment, String toolCode) {
        ResponseEntity<List<Transaction>> restExchange =
                restTemplate.exchange(
                        "http://my-gateway-server/transactions/transactions/worker" +
                                "?anotherDepartment={anotherDepartment}&toolCode={toolCode}",
                        HttpMethod.GET,
                        null, new ParameterizedTypeReference<>() {
                        }, anotherDepartment, toolCode);
        return restExchange.getBody();
    }

}
