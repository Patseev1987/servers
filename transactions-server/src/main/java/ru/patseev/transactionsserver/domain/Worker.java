package ru.patseev.transactionsserver.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.patseev.transactionsserver.domain.enums.Department;
import ru.patseev.transactionsserver.domain.enums.WorkerType;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "workers")
@NoArgsConstructor
public class Worker {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

}
