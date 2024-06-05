package ru.patseev.jaws_server.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "jaws")
public class Jaw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "operation_number", nullable = false)
    private String operationNumber;

    @Column(name = "description")
    private String description;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "shelf", column = @Column(name = "place_shelf")),
            @AttributeOverride(name = "column", column = @Column(name = "place_column")),
            @AttributeOverride(name = "row", column = @Column(name = "place_row"))
    })
    private Place place;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "jaw")
    private List<PhotoJaws> photos;

}
