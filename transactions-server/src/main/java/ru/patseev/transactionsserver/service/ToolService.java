package ru.patseev.transactionsserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patseev.transactionsserver.domain.Tool;
import ru.patseev.transactionsserver.repository.ToolRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ToolService {
    private final ToolRepository toolRepository;
    //add tool
    public Tool createTool(Tool tool) {
        return toolRepository.save(tool);
    }
    //get tool by Code
    public Optional<Tool> getToolByCode(String code) {
        return toolRepository.findByCode(code);
    }
    //get tools
    public List<Tool> getAllTools() {
        return toolRepository.findAll();
    }

}
