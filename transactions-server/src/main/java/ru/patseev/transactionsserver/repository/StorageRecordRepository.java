package ru.patseev.transactionsserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.patseev.transactionsserver.domain.*;

import java.util.List;
import java.util.Optional;


public interface StorageRecordRepository extends JpaRepository<StorageRecord, Long> {

    List<StorageRecord> findAllByWorkerId(Long workerId);

}
