package ru.patseev.recordsserver.utils;


import org.springframework.stereotype.Component;
import ru.patseev.recordsserver.domain.Tool;
import ru.patseev.recordsserver.domain.Worker;
import ru.patseev.recordsserver.dto.ToolDTO;
import ru.patseev.recordsserver.dto.WorkerDTO;

import java.io.IOException;

@Component

public class MyMapper {

    public ToolDTO toToolDTO(Tool tool) {
        return ToolDTO.builder()
                .code(tool.getCode())
                .name(tool.getName())
                .build();
    }

    public WorkerDTO toWorkerDTO(Worker worker) {
        return WorkerDTO.builder()
                .id(worker.getId())
                .department(worker.getDepartment())
                .firstName(worker.getFirstName())
                .lastName(worker.getLastName())
                .build();
    }

}
