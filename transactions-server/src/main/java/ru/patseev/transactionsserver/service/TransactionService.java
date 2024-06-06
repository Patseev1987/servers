package ru.patseev.transactionsserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patseev.transactionsserver.domain.enums.Department;
import ru.patseev.transactionsserver.domain.StorageRecord;
import ru.patseev.transactionsserver.domain.Transaction;
import ru.patseev.transactionsserver.repository.TransactionsRepository;
import ru.patseev.transactionsserver.retrofit.ApiFactory;
import ru.patseev.transactionsserver.utils.MyMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static ru.patseev.transactionsserver.domain.enums.Department.DEPARTMENT_19;
import static ru.patseev.transactionsserver.domain.enums.Department.SHARPENING;


@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionsRepository transactionsRepository;
    private final ApiFactory api;
    private final MyMapper mapper;


    public Transaction createTransaction(Transaction transaction) {
        changeStorageRecord(transaction);
        return transactionsRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionsRepository.findAll();
    }


    public void changeStorageRecord(Transaction transaction) {
        StorageRecord senderStorageRecord;
        StorageRecord receiverStorageRecord;
        try {
            senderStorageRecord = api.getApiRecords()
                    .getRecordByWorkerIdAndToolCode(
                            transaction.getSender().getId(),
                            transaction.getTool().getCode()
                    ).execute().body();
            System.out.println(senderStorageRecord);

            receiverStorageRecord = api.getApiRecords()
                    .getRecordByWorkerIdAndToolCode(
                            transaction.getReceiver().getId(),
                            transaction.getTool().getCode()
                    ).execute().body();
            System.out.println(receiverStorageRecord);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        if (senderStorageRecord.getId() != -1) {
            int newValue = senderStorageRecord.getAmount() - transaction.getAmount();
            senderStorageRecord.setAmount(newValue);
            if (newValue < 0) {
                throw new RuntimeException("Negative amount not allowed");
            }
            api.getApiRecords().addRecord(senderStorageRecord);
        } else {
            throw new RuntimeException("sender storage record not found");
        }
        if (receiverStorageRecord.getId() != -1) {
            int newValue = receiverStorageRecord.getAmount() + transaction.getAmount();
            receiverStorageRecord.setAmount(newValue);
            api.getApiRecords().addRecord(receiverStorageRecord);
        } else {
            var transactionDTO = mapper.toTransactionDTO(transaction);
            var newStorageRecord = new StorageRecord();
            newStorageRecord.setAmount(transactionDTO.getAmount());
            newStorageRecord.setTool(transactionDTO.getTool());
            newStorageRecord.setWorker(transactionDTO.getReceiver());
            api.getApiRecords().addRecord(newStorageRecord);
        }
    }

    public List<Transaction> getTransactionsBySurnameSenderAndReceiver(Long workerId) {
        return transactionsRepository.findTransactionsBySurnameSenderAndReceiver(workerId);
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
                DEPARTMENT_19, SHARPENING, toolCode
        );
        var fromSharpen = getTransactionsBySenderDepartmentAndReceiverDepartment(
                SHARPENING, DEPARTMENT_19, toolCode
        );
        result.addAll(toSharpen);
        result.addAll(fromSharpen);

        return result.stream().sorted(Comparator.comparing(Transaction::getTransactionDate).reversed()).toList();
    }

}
