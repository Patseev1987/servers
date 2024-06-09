package ru.patseev.securityauthserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patseev.securityauthserver.dto.Transaction;
import ru.patseev.securityauthserver.dto.enums.Department;
import ru.patseev.securityauthserver.service.clients.RestTemplateTransactionClient;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TransactionService {
    private final RestTemplateTransactionClient rest;

    //get all transactions
    public List<Transaction> getStorageTransactions() {
        return rest.getStorageTransactions();
    }

    //create transaction
    public Transaction createTransaction(Transaction transaction) {
        return rest.createTransaction(transaction);
    }

    //get transactions by worker id
    public List<Transaction> getTransactionsByWorkerId(Long workerId) {
        return rest.getTransactionsByWorkerId(workerId);
    }

    //get transaction with another departments
    public List<Transaction> getTransactionsWitheAnotherDepartment(Department anotherDepartment, String toolCode) {
        return rest.getTransactionsWitheAnotherDepartment(anotherDepartment, toolCode);
    }
}
