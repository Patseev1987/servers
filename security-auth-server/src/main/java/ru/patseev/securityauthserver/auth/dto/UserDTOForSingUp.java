package ru.patseev.securityauthserver.auth.dto;


import ru.patseev.securityauthserver.dto.enums.Department;
import ru.patseev.securityauthserver.dto.enums.WorkerType;


import java.time.LocalDate;

public record UserDTOForSingUp(
        String username,
        String password,
        String firstName,
        String lastName,
        String patronymic,
        WorkerType type,
        LocalDate joinDate,
        Department department) {
}
