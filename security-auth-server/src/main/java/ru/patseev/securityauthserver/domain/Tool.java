package ru.patseev.securityauthserver.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.patseev.securityauthserver.domain.enums.ToolType;


@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
public class Tool {

    private String code;
    private String name;
    private String description;
    private String additionalInfo;
    private String icon;
    private Place place;
    private ToolType type;

}
