package ru.patseev.transactionsserver.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "workers_table")
@NoArgsConstructor
public class Worker {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String patronymic;
    @Enumerated(EnumType.STRING)
    private WorkerType type;
    @Column(name = "join_date")
    private LocalDate joinDate;
    @Enumerated(EnumType.STRING)
    private Department department;
    private String login;
    private String password;
}
