package ru.patseev.jaws_server.dto;

import lombok.Builder;
import lombok.Data;
import ru.patseev.jaws_server.domain.Place;


import java.util.List;

@Data
@Builder
public class JawDTO {

    private Long id;

    private String name;

    private String operationNumber;

    private String description;

    private Place place;

    private List<String> photosUrls;
}
