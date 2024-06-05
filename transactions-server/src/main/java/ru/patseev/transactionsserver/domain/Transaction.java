package ru.patseev.transactionsserver.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table( name = "transactions_table")
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
    private LocalDate transactionDate;
}
