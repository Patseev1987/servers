package ru.patseev.jaws_server.dto;

import lombok.*;
import ru.patseev.jaws_server.domain.Place;

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
