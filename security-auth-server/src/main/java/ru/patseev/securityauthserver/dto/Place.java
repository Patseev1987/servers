package ru.patseev.securityauthserver.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place {
    private String shelf;
    private String column;
    private String row;
}
