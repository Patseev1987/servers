package ru.patseev.transactionsserver.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
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
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Worker sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Worker receiver;
    private Integer amount;
    @ManyToOne
    @JoinColumn(name = "tool_code")
    private Tool tool;
    @Column(name = "transaction_date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate transactionDate;
}
