package ru.patseev.transactionsserver.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tool {

    private String code;
    @Transient
    private String name;
    @Transient
    private String description;
    @Transient
    private String additionalInfo;
    @Transient
    private String icon;
    @Transient
    private Place place;
    @Transient
    private ToolType type;

}
