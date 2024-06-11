package ru.patseev.transactionsserver.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.patseev.transactionsserver.domain.Transaction;
import ru.patseev.transactionsserver.dto.ToolDTO;
import ru.patseev.transactionsserver.dto.TransactionDTO;
import ru.patseev.transactionsserver.dto.WorkerDTO;
import ru.patseev.transactionsserver.service.client.RestTemplateClient;

@Component
@RequiredArgsConstructor
public class MyMapper {
    private final RestTemplateClient restTemplateClient;

    public TransactionDTO toTransactionDTO(Transaction transaction) {
        WorkerDTO sender = restTemplateClient.getWorkerByWorkerId(transaction.getSender().getId());
        WorkerDTO receiver = restTemplateClient.getWorkerByWorkerId(transaction.getReceiver().getId());
        ToolDTO tool = restTemplateClient.getToolByToolCode(transaction.getTool().getCode());

        return TransactionDTO.builder()
                .id(transaction.getId())
                .transactionDate(transaction.getTransactionDate())
                .amount(transaction.getAmount())
                .sender(sender)
                .receiver(receiver)
                .tool(tool)
                .build();
    }
}
