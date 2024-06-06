package ru.patseev.transactionsserver.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.patseev.transactionsserver.domain.Tool;
import ru.patseev.transactionsserver.domain.Transaction;
import ru.patseev.transactionsserver.domain.Worker;
import ru.patseev.transactionsserver.dto.ToolDTO;
import ru.patseev.transactionsserver.dto.TransactionDTO;
import ru.patseev.transactionsserver.dto.WorkerDTO;
import ru.patseev.transactionsserver.retrofit.ApiFactory;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class MyMapper {
    private final ApiFactory api;

    public TransactionDTO toTransactionDTO(Transaction transaction) {
        WorkerDTO sender;
        WorkerDTO receiver;
        ToolDTO tool;
        try {
            sender = api.getApiRecords().getWorkerById(transaction.getSender().getId()).execute().body();
            receiver = api.getApiRecords().getWorkerById(transaction.getReceiver().getId()).execute().body();
            tool = api.getApiRecords().getToolByCode(transaction.getTool().getCode()).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return TransactionDTO.builder()
                .id(transaction.getId())
                .transactionDate(transaction.getTransactionDate())
                .amount(transaction.getAmount())
                .sender(sender)
                .receiver(receiver)
                .tool(tool)
                .build();

    }

    public Transaction toTransaction(TransactionDTO transactionDTO) {
        return Transaction.builder()
                .amount(transactionDTO.getAmount())
                .transactionDate(transactionDTO.getTransactionDate())
                .sender(toWorker(transactionDTO.getSender()))
                .receiver(toWorker(transactionDTO.getReceiver()))
                .tool(toTool(transactionDTO.getTool()))
                .build();
    }



    public Worker toWorker(WorkerDTO workerDTO) {
        return Worker.builder()
                .id(workerDTO.getId())
                .department(workerDTO.getDepartment())
                .firstName(workerDTO.getFirstName())
                .lastName(workerDTO.getLastName())
                .build();
    }

    public Tool toTool(ToolDTO toolDTO) {
        return Tool.builder()
                .code(toolDTO.getCode())
                .name(toolDTO.getName())
                .build();
    }

}
