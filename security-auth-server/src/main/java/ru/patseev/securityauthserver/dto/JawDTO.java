package ru.patseev.securityauthserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class JawDTO {

    private Long id;

    private String name;

    private String operationNumber;

    private String description;

    private Place place;

    private List<String> photosUrls;
}
