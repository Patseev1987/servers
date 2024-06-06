package ru.patseev.transactionsserver.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.patseev.transactionsserver.dto.ToolDTO;
import ru.patseev.transactionsserver.dto.WorkerDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StorageRecord {

    private Long id;
    private ToolDTO tool;
    private WorkerDTO worker;
    private Integer amount;
}
