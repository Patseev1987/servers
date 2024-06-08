package ru.patseev.securityauthserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.patseev.securityauthserver.domain.enums.Department;
import ru.patseev.securityauthserver.domain.enums.WorkerType;
import ru.patseev.securityauthserver.service.utils.LocalDateDeserializer;
import ru.patseev.securityauthserver.service.utils.LocalDateSerializer;


import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Worker {

    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private WorkerType type;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate joinDate;
    private Department department;
    private String login;
}
