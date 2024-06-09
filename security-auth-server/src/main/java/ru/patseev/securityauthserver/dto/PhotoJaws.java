package ru.patseev.securityauthserver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhotoJaws {
    private Long id;
    private LocalDateTime createDate;
    private String url;
    private String fileName;
    private Jaw jaw;
}
