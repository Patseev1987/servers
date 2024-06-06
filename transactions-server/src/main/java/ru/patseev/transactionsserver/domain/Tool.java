package ru.patseev.transactionsserver.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.patseev.transactionsserver.domain.enums.ToolType;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tools_table")
@Builder
@AllArgsConstructor
public class Tool {
    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
}
