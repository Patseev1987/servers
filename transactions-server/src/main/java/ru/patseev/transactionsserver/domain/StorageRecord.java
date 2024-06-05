package ru.patseev.transactionsserver.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "storage_record_table")

public class StorageRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    @AttributeOverride(name = "code", column = @Column(name = "tool_code"))
    private Tool tool;
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "worker_id"))
    private Worker worker;
    @Column(name = "amount")
    private Integer amount;
}
