package ru.patseev.transactionsserver.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.patseev.transactionsserver.service.TransactionService;
import ru.patseev.transactionsserver.domain.enums.Department;
import ru.patseev.transactionsserver.domain.Transaction;

import java.util.List;

import static ru.patseev.transactionsserver.domain.enums.Department.*;


@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/transaction/create")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }


    @GetMapping("/transactions/worker")
    public List<Transaction> getTransactionsBySurname(
            @RequestParam(name = "workerId") Long workerId,
            @RequestParam(value = "page",defaultValue = "0") Integer page
    ) {
        return transactionService.getTransactionsBySurnameSenderAndReceiver(workerId,page);
    }

    @GetMapping("/transactions/actionWithAnotherDepartments")
    public List<Transaction> getDecommissionedTools(
            @RequestParam(name = "anotherDepartment") Department anotherDepartment,
            @RequestParam(name = "toolCode", defaultValue = "") String toolCode
    ) {
        if (anotherDepartment == STORAGE_OF_DECOMMISSIONED_TOOLS) {
            return transactionService.getTransactionsBySenderDepartmentAndReceiverDepartment(
                    DEPARTMENT_19, STORAGE_OF_DECOMMISSIONED_TOOLS,toolCode
            );
        } else if (anotherDepartment == SHARPENING) {
            return transactionService.getTransactionsWithSharpening(toolCode);
        }else {
            return transactionService.getTransactionsBySenderDepartmentAndReceiverDepartment(
                    MAIN_STORAGE, DEPARTMENT_19,toolCode
            );
        }
    }
}
