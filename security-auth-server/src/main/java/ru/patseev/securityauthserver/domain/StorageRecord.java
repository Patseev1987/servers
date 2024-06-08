package ru.patseev.securityauthserver.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StorageRecord {

    private Long id;
    private Tool tool;
    private Worker worker;
    private Integer amount;
}
