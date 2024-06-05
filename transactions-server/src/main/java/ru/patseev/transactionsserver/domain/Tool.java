package ru.patseev.transactionsserver.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tools_table")
public class Tool {
    @Id
    private String code;
    private String name;
    private String description;
    @Column(name = "additional_info")
    private String additionalInfo;
    private String icon;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "shelf", column = @Column(name = "place_shelf")),
            @AttributeOverride( name = "column", column = @Column(name = "place_column")),
            @AttributeOverride( name = "row", column = @Column(name = "place_row"))
    })
    private Place place;
    @Enumerated (EnumType.STRING)
    private ToolType type;

}
