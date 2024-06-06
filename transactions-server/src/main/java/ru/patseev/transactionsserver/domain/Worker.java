package ru.patseev.transactionsserver.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.patseev.transactionsserver.domain.enums.Department;



@Data
@Entity
@Table(name = "workers_transaction")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Worker {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column (name = "department")
    @Enumerated(EnumType.STRING)
    private Department department;

}
