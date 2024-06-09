package ru.patseev.securityauthserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Jaw {
    private Long id;
    private String name;
    private String operationNumber;
    private String description;
    private Place place;
    private List<PhotoJaws> photos;

}
