package ru.patseev.securityauthserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.patseev.securityauthserver.dto.enums.Department;
import ru.patseev.securityauthserver.dto.enums.WorkerType;
import ru.patseev.securityauthserver.service.utils.LocalDateDeserializer;
import ru.patseev.securityauthserver.service.utils.LocalDateSerializer;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Department department;
}
