package ru.patseev.securityauthserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import ru.patseev.securityauthserver.service.utils.LocalDateDeserializer;
import ru.patseev.securityauthserver.service.utils.LocalDateSerializer;


import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
    private Long id;
    private WorkerResponse sender;
    private WorkerResponse receiver;
    private Integer amount;
    private ToolResponse tool;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate transactionDate;
}
