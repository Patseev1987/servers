package ru.patseev.transactionsserver.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.patseev.transactionsserver.domain.enums.Department;
import ru.patseev.transactionsserver.domain.enums.WorkerType;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private WorkerType type;

    private LocalDate joinDate;
    private Department department;
    private String login;
    private String password;
}
