package ru.patseev.transactionsserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.patseev.transactionsserver.domain.enums.Department;
import ru.patseev.transactionsserver.domain.Transaction;
import ru.patseev.transactionsserver.dto.StorageRecordDTO;
import ru.patseev.transactionsserver.repository.TransactionsRepository;
import ru.patseev.transactionsserver.service.client.RestTemplateClient;
import ru.patseev.transactionsserver.utils.MyMapper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import static ru.patseev.transactionsserver.domain.enums.Department.DEPARTMENT_19;
import static ru.patseev.transactionsserver.domain.enums.Department.SHARPENING;


@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionsRepository transactionsRepository;
    private final MyMapper mapper;
    private final RestTemplateClient restTemplateClient;

    //create transactions
    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        transaction.setTransactionDate(LocalDate.now());
        changeStorageRecord(transaction);
        return transactionsRepository.save(transaction);
    }

    //get all transactions
    public List<Transaction> getAllTransactions() {
        return transactionsRepository.findAll();
    }

    //update records
    @Transactional
    public void changeStorageRecord(Transaction transaction) {
        StorageRecordDTO senderStorageRecord = restTemplateClient.getRecordByWorkerIdAndToolCode(
                transaction.getSender().getId(),
                transaction.getTool().getCode()
        );
        StorageRecordDTO receiverStorageRecord = restTemplateClient.getRecordByWorkerIdAndToolCode(
                transaction.getReceiver().getId(),
                transaction.getTool().getCode()
        );

        if ((receiverStorageRecord != null) && (senderStorageRecord != null)
                && (Objects.equals(senderStorageRecord.getId(), receiverStorageRecord.getId()))) {
            throw new RuntimeException("You can't be sender and receiver at the same time");
        }

        if (senderStorageRecord != null && senderStorageRecord.getId() != -1) {
            int newValue = senderStorageRecord.getAmount() - transaction.getAmount();
            senderStorageRecord.setAmount(newValue);
            if (newValue < 0) {
                throw new RuntimeException("Negative amount not allowed");
            }
            var sender = restTemplateClient.updateRecord(senderStorageRecord);
            System.out.println("sender records update -> " + sender);

        }
        if (receiverStorageRecord != null && receiverStorageRecord.getId() != -1) {
            int newValue = receiverStorageRecord.getAmount() + transaction.getAmount();
            receiverStorageRecord.setAmount(newValue);
            var receiver = restTemplateClient.updateRecord(receiverStorageRecord);
            System.out.println("sender records update -> " + receiver);

        } else {
            var transactionDTO = mapper.toTransactionDTO(transaction);
            var newStorageRecord = new StorageRecordDTO();
            newStorageRecord.setAmount(transactionDTO.getAmount());
            newStorageRecord.setTool(transactionDTO.getTool());
            newStorageRecord.setWorker(transactionDTO.getReceiver());
            var newRecord = restTemplateClient.addRecord(newStorageRecord);
            System.out.println("sender records update -> " + newRecord);

        }
    }

    public List<Transaction> findTransactionsBySenderIdAndReceiverId(Long workerId) {
        return transactionsRepository.findTransactionsBySenderIdAndReceiverId(workerId);
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
