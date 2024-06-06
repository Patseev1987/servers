package ru.patseev.recordsserver.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.patseev.recordsserver.domain.enums.Department;




@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class WorkerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private Department department;

}
