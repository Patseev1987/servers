package ru.patseev.transactionsserver.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.patseev.transactionsserver.domain.enums.ToolType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToolDTO {

    private String code;
    private String name;
    private String description;
    private String additionalInfo;
    private String icon;
    private PlaceDTO place;
    private ToolType type;

}
