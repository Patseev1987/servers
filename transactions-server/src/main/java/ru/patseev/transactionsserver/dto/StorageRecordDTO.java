package ru.patseev.transactionsserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class StorageRecordDTO {

    private Long id;
    private ToolDTO tool;
    private WorkerDTO worker;
    private Integer amount;
}
