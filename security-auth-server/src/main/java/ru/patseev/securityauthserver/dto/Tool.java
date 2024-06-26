package ru.patseev.securityauthserver.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.patseev.securityauthserver.dto.enums.ToolType;


@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tool {

    private String code;
    private String name;
    private String description;
    private String additionalInfo;
    private String icon;
    private Place place;
    private ToolType type;

}
