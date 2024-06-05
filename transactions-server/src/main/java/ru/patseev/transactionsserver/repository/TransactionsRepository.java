package ru.patseev.transactionsserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.patseev.transactionsserver.domain.Department;
import ru.patseev.transactionsserver.domain.Transaction;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transaction, Long> {

    @Query("from Transaction T where T.sender.id =:workerId or T.receiver.id =:workerId order by " +
            "date(T.transactionDate) desc limit 200 offset :offset ")
    List<Transaction> findTransactionsBySurnameSenderAndReceiver(Long workerId, Integer offset);

    @Query("from Transaction  T where T.sender.department =:senderDepartment and " +
            "T.receiver.department =:receiverDepartment and T.tool.code like %:toolCode% order by " +
            "date(T.transactionDate) desc limit 200")
    List<Transaction> findAllTransactionsBySenderDepartmentAndReceiverDepartment(Department senderDepartment,
                                                                                 Department receiverDepartment,
                                                                                 String toolCode);
}
