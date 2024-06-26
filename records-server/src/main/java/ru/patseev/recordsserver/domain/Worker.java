package ru.patseev.recordsserver.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.patseev.recordsserver.domain.enums.Department;
import ru.patseev.recordsserver.domain.enums.WorkerType;
import ru.patseev.recordsserver.utils.LocalDateDeserializer;
import ru.patseev.recordsserver.utils.LocalDateSerializer;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "workers")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Worker {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "patronymic")
    private String patronymic;
    @Enumerated(EnumType.STRING)
    private WorkerType type;
    @Column(name = "join_date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate joinDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "department")
    private Department department;
    @Column(name = "login")
    private String login;
}
