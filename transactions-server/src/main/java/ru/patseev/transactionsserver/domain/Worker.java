package ru.patseev.transactionsserver.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Worker {

    private Long id;
    @Transient
    private String firstName;
    @Transient
    private String lastName;
    @Transient
    private String patronymic;
    @Transient
    private WorkerType type;
    @Transient
    private LocalDate joinDate;
    @Transient
    private Department department;
    @Transient
    private String login;

}
