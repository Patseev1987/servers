package ru.patseev.recordsserver.domain;


import jakarta.persistence.*;
import lombok.*;
import ru.patseev.recordsserver.domain.enums.ToolType;



@NoArgsConstructor
@Entity
@Table(name = "tools")
@Builder
@Data
@AllArgsConstructor
public class Tool {
    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "additional_info")
    private String additionalInfo;
    @Column(name = "icon")
    private String icon;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "shelf", column = @Column(name = "place_shelf")),
            @AttributeOverride(name = "column", column = @Column(name = "place_column")),
            @AttributeOverride(name = "row", column = @Column(name = "place_row"))
    })
    private Place place;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ToolType type;

}
