package ru.patseev.securityauthserver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
