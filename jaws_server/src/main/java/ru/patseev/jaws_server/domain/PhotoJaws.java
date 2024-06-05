package ru.patseev.jaws_server.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "jaw_photos")
public class PhotoJaws {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "create_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    @Column(name = "url")
    private String url;

    @Column (name = "file_name")
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "jaws_id")
    private Jaw jaw;

}
