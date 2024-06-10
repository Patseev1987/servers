package ru.patseev.jaws_server.utils;


import ru.patseev.jaws_server.domain.Jaw;
import ru.patseev.jaws_server.domain.PhotoJaws;
import ru.patseev.jaws_server.dto.JawDTO;
import ru.patseev.jaws_server.dto.PhotoJawsDTO;

public class Mapper {
    public static JawDTO JawToJawDTO2(Jaw jaw) {
        return JawDTO.builder()
                .id(jaw.getId())
                .name(jaw.getName())
                .place(jaw.getPlace())
                .description(jaw.getDescription())
                .operationNumber(jaw.getOperationNumber())
                .build();
    }

    public static PhotoJawsDTO toPhotoJawsDTO(PhotoJaws photoJaws) {
        return PhotoJawsDTO.builder()
                .fileName(photoJaws.getFileName())
                .createDate(photoJaws.getCreateDate())
                .url(photoJaws.getUrl())
                .build();
    }
}
