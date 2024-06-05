package ru.patseev.recordsserver.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "storage_records")
@AllArgsConstructor
@Builder
public class StorageRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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
