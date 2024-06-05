package ru.patseev.transactionsserver.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Embedded
    @AttributeOverride(name = "id",column = @Column(name = "sender_id"))
    private Worker sender;
    @Embedded
    @AttributeOverride(name = "id",column = @Column(name = "receiver_id"))
    private Worker receiver;
    private Integer amount;
    @Embedded
    @AttributeOverride(name = "code",column = @Column(name = "tool_code"))
    private Tool tool;
    @Column(name = "transaction_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate transactionDate;
}
