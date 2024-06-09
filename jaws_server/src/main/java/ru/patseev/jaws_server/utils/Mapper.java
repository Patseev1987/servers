package ru.patseev.jaws_server.utils;


import ru.patseev.jaws_server.domain.Jaw;
import ru.patseev.jaws_server.dto.JawDTO;

public class Mapper {
    public static JawDTO JawToJawDTO(Jaw jaw) {
        return JawDTO.builder()
                .id(jaw.getId())
                .name(jaw.getName())
                .place(jaw.getPlace())
                .description(jaw.getDescription())
                .operationNumber(jaw.getOperationNumber())
                .build();
    }
}
