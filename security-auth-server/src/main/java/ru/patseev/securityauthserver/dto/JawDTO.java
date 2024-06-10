package ru.patseev.securityauthserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JawDTO {
    private Long id;

    private String name;

    private String operationNumber;

    private String description;

    private Place place;

    public List<PhotoJawsDTO> photos;
}
