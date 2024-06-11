package ru.patseev.recordsserver.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.patseev.recordsserver.domain.Tool;
import ru.patseev.recordsserver.services.ToolService;

import java.util.List;

@RestController
@RequestMapping("/records/tools")
@RequiredArgsConstructor
public class ToolController {
    private final ToolService toolService;
    //get all tools
    @GetMapping
    public List<Tool> getAllTools() {
        return toolService.getAllTools();
    }
    //get all tools which contain code
    @GetMapping("/code")
    public List<Tool> getToolByCodeLike(@RequestParam(name = "code") String code) {
       return toolService.getToolByCodeLike(code);
    }
    //get tool by code
    @GetMapping("/{code}")
    public Tool getToolByCode(@PathVariable String code) {
        return toolService.getToolByCode(code);
    }
    //add tool
    @PostMapping("/add")
    public Tool addTool(@RequestBody Tool tool) {
        return toolService.addTool(tool);
    }
    //update tool
    @PutMapping("/update")
    public Tool updateTool(@RequestBody Tool tool) {
        return toolService.updateTool(tool);
    }

}
