package ru.patseev.transactionsserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patseev.transactionsserver.domain.*;
import ru.patseev.transactionsserver.repository.StorageRecordRepository;
import ru.patseev.transactionsserver.retrofit.ApiFactory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StorageRecordService {
    private final StorageRecordRepository storageRecordRepository;
    private static final String BLANK_CODE = "";

    public List<StorageRecord> getAllRecords() {
        return storageRecordRepository.findAll();
    }



}
