package ru.patseev.transactionsserver.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDTO {
    private String shelf;
    private String column;
    private String row;
}
