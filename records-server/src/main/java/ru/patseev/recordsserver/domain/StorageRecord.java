package ru.patseev.recordsserver.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "storage_record_table")

public class StorageRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "tool_code")
    private Tool tool;
    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;
    @Column(name = "amount")
    private Integer amount;
}
