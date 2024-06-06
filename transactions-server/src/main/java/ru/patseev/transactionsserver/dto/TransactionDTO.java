package ru.patseev.transactionsserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDTO {
    private Long id;
    private WorkerDTO sender;
    private WorkerDTO receiver;
    private Integer amount;
    private ToolDTO tool;
    private LocalDate transactionDate;
}
