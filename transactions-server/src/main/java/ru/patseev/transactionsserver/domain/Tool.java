package ru.patseev.transactionsserver.domain;


import jakarta.persistence.*;
import lombok.*;
import ru.patseev.transactionsserver.domain.enums.ToolType;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tools_transactions")
@Builder
@AllArgsConstructor
public class Tool {
    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
}
