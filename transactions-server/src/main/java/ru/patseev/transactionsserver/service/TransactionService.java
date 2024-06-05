package ru.patseev.transactionsserver.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patseev.transactionsserver.domain.Department;
import ru.patseev.transactionsserver.domain.StorageRecord;
import ru.patseev.transactionsserver.domain.Transaction;
import ru.patseev.transactionsserver.repository.TransactionsRepository;
import ru.patseev.transactionsserver.retrofit.ApiFactory;
import ru.patseev.transactionsserver.retrofit.ApiRecords;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static ru.patseev.transactionsserver.domain.Department.DEPARTMENT_19;
import static ru.patseev.transactionsserver.domain.Department.SHARPENING;


@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionsRepository transactionsRepository;
    private final ApiFactory api;


    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        changeStorageRecord(transaction);
        return transactionsRepository.save(transaction);
    }


    public List<Transaction> getAllTransactions() {
        return transactionsRepository.findAll();
    }

    @Transactional
    void changeStorageRecord(Transaction transaction) {
        StorageRecord senderStorageRecord;
        StorageRecord receiverStorageRecord;
        try {
            senderStorageRecord = api.getApiTools()
                    .getRecordByWorkerIdAndToolCode(
                            transaction.getSender().getId(),
                            transaction.getTool().getCode()
                    ).execute().body();
           receiverStorageRecord = api.getApiTools()
                   .getRecordByWorkerIdAndToolCode(
                           transaction.getReceiver().getId(),
                           transaction.getTool().getCode()
                   ).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        if (senderStorageRecord != null) {
            int newValue = senderStorageRecord.getAmount() - transaction.getAmount();
            senderStorageRecord.setAmount(newValue);
            if (newValue < 0) {
                throw new RuntimeException("Negative amount not allowed");
            }
            api.getApiTools().addRecord(senderStorageRecord);
        } else {
            throw new RuntimeException("sender storage record not found");
        }
        if (receiverStorageRecord != null ) {
            int newValue = receiverStorageRecord.getAmount() + transaction.getAmount();
            receiverStorageRecord.setAmount(newValue);
            api.getApiTools().addRecord(receiverStorageRecord);
        } else {
            var newStorageRecord = new StorageRecord();
            newStorageRecord.setAmount(transaction.getAmount());
            newStorageRecord.setTool(transaction.getTool());
            newStorageRecord.setWorker(transaction.getReceiver());
            api.getApiTools().addRecord(newStorageRecord);
        }
    }

    public List<Transaction> getTransactionsBySurnameSenderAndReceiver(Long workerId, Integer page) {
        Integer offset = page * 200;
        return transactionsRepository.findTransactionsBySurnameSenderAndReceiver(workerId, offset);
    }

    public List<Transaction> getTransactionsBySenderDepartmentAndReceiverDepartment(Department senderDepartment,
                                                                                    Department receiverDepartment,
                                                                                    String toolCode) {
        return transactionsRepository
                .findAllTransactionsBySenderDepartmentAndReceiverDepartment(senderDepartment,
                        receiverDepartment,
                        toolCode);
    }

    public List<Transaction> getTransactionsWithSharpening(String toolCode) {
        var result = new ArrayList<Transaction>();
        var toSharpen = getTransactionsBySenderDepartmentAndReceiverDepartment(
                DEPARTMENT_19, SHARPENING,toolCode
        );
        var fromSharpen = getTransactionsBySenderDepartmentAndReceiverDepartment(
                SHARPENING, DEPARTMENT_19,toolCode
        );
        result.addAll(toSharpen);
        result.addAll(fromSharpen);

        return result.stream().sorted(Comparator.comparing(Transaction::getTransactionDate).reversed()).toList();
    }

}
