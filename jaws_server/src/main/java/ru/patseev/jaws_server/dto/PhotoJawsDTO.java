package ru.patseev.jaws_server.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import ru.patseev.jaws_server.domain.Jaw;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PhotoJawsDTO {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    private String url;
    private String fileName;
}
