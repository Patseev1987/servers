package ru.patseev.recordsserver.services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patseev.recordsserver.domain.Tool;
import ru.patseev.recordsserver.repositoryies.ToolRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ToolService {
    private final ToolRepository toolRepository;
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
        return toolRepository.save(tool);
    }
    //delete tool by code
    public void deleteTool(String code) {
        toolRepository.deleteByCode(code);
    }

}




