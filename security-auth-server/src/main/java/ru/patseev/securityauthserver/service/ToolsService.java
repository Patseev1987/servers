package ru.patseev.securityauthserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patseev.securityauthserver.dto.Tool;
import ru.patseev.securityauthserver.service.clients.RestTemplateToolClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToolsService {
    private final RestTemplateToolClient restTemplate;

    //get all tools
    public List<Tool> getTools() {
        return restTemplate.getTools();
    }

    //get tools which contain code
    public List<Tool> getToolsWithCode(String code) {
        return restTemplate.getToolsWithCode(code);
    }

    //get tool by code
    public Tool getToolByCode(String code) {
        return restTemplate.getToolByCode(code);
    }

    //add tool
    public Tool addTool(Tool tool) {
        return restTemplate.addTool(tool);
    }

    //update tool
    public Tool updateTool(Tool tool) {
        return restTemplate.updateTool(tool);
    }
}
