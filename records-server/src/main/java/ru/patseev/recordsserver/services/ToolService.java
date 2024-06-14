package ru.patseev.recordsserver.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patseev.recordsserver.domain.Place;
import ru.patseev.recordsserver.domain.Tool;
import ru.patseev.recordsserver.domain.enums.ToolType;
import ru.patseev.recordsserver.repositoryies.ToolRepository;
import ru.patseev.recordsserver.services.client.RestTemplateClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToolService {
    private final ToolRepository toolRepository;
    private final RestTemplateClient restTemplateClient;


    //get all tools
    public List<Tool> getAllTools() {
        return toolRepository.findAll();
    }

    //get all tools which contain code
    public List<Tool> getToolByCodeLike(String code) {
        return toolRepository.findAllByCodeLike(code);
    }

    //get tool by code
    public Tool getToolByCode(String code) {
        return toolRepository.findByCode(code);
    }

    //add tool
    public Tool addTool(Tool tool) {
        restTemplateClient.addTool(tool);
        return toolRepository.save(tool);
    }

    //update tool
    public Tool updateTool(Tool tool) {
        restTemplateClient.updateTool(tool);
        return toolRepository.save(tool);
    }
}




