package ru.patseev.securityauthserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.patseev.securityauthserver.dto.Transaction;
import ru.patseev.securityauthserver.dto.enums.Department;
import ru.patseev.securityauthserver.service.TransactionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    //get all transactions
    public List<Transaction> getStorageTransactions() {
        return transactionService.getStorageTransactions();
    }

    @PostMapping("/create")
    //create transaction
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    @GetMapping("/transactions_by_worker_id")
    //get transactions by worker id
    public List<Transaction> getTransactionsByWorkerId(@RequestParam(name = "workerId") Long workerId) {
        return transactionService.getTransactionsByWorkerId(workerId);
    }

    @GetMapping("/transactions_with_another_department")
    //get transaction with another departments
    public List<Transaction> getTransactionsWitheAnotherDepartment(
           @RequestParam(name = "anotherDepartment") Department anotherDepartment,
           @RequestParam(name = "toolCode") String toolCode) {
        return transactionService.getTransactionsWitheAnotherDepartment(anotherDepartment, toolCode);
    }
}
