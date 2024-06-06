package ru.patseev.transactionsserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patseev.transactionsserver.domain.enums.Department;
import ru.patseev.transactionsserver.domain.Transaction;
import ru.patseev.transactionsserver.dto.StorageRecordDTO;
import ru.patseev.transactionsserver.repository.TransactionsRepository;
import ru.patseev.transactionsserver.retrofit.ApiFactory;
import ru.patseev.transactionsserver.utils.MyMapper;

import java.io.IOException;
import java.time.LocalDate;
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
        transaction.setTransactionDate(LocalDate.now());
        changeStorageRecord(transaction);
        return transactionsRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionsRepository.findAll();
    }


    private void changeStorageRecord(Transaction transaction) {
        StorageRecordDTO senderStorageRecord;
        StorageRecordDTO receiverStorageRecord;
        try {
            senderStorageRecord = api.getApiRecords()
                    .getRecordByWorkerIdAndToolCode(
                            transaction.getSender().getId(),
                            transaction.getTool().getCode()
                    ).execute().body();
            System.out.println("sender storage records -> " + senderStorageRecord);

            receiverStorageRecord = api.getApiRecords()
                    .getRecordByWorkerIdAndToolCode(
                            transaction.getReceiver().getId(),
                            transaction.getTool().getCode()
                    ).execute().body();
            System.out.println(receiverStorageRecord);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        if (senderStorageRecord != null && senderStorageRecord.getId() != -1) {
            int newValue = senderStorageRecord.getAmount() - transaction.getAmount();
            senderStorageRecord.setAmount(newValue);
            if (newValue < 0) {
                throw new RuntimeException("Negative amount not allowed");
            }
          var updateSender = api.getApiRecords().updateRecord(senderStorageRecord);
            try {
                System.out.println("sender record update -> " + updateSender.execute().body());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (receiverStorageRecord != null && receiverStorageRecord.getId() != -1) {
            int newValue = receiverStorageRecord.getAmount() + transaction.getAmount();
            receiverStorageRecord.setAmount(newValue);
          var updateReceiver = api.getApiRecords().addRecord(receiverStorageRecord);
            try {
                System.out.println("update record receiver -> " + updateReceiver.execute().body());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            var transactionDTO = mapper.toTransactionDTO(transaction);
            var newStorageRecord = new StorageRecordDTO();
            newStorageRecord.setAmount(transactionDTO.getAmount());
            newStorageRecord.setTool(transactionDTO.getTool());
            newStorageRecord.setWorker(transactionDTO.getReceiver());
          var record = api.getApiRecords().addRecord(newStorageRecord);
            try {
                System.out.println("record -> " + record.execute().body());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
