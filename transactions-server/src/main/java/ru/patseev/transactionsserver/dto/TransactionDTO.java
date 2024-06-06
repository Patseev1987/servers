package ru.patseev.transactionsserver.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.patseev.transactionsserver.utils.LocalDateDeserializer;
import ru.patseev.transactionsserver.utils.LocalDateSerializer;

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
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate transactionDate;
}
