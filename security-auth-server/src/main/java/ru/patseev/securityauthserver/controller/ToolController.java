package ru.patseev.securityauthserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.patseev.securityauthserver.dto.Tool;
import ru.patseev.securityauthserver.service.ToolsService;

import java.util.List;

@RestController
@RequestMapping("/tools")
@RequiredArgsConstructor
public class ToolController {
    private final ToolsService toolService;

    @GetMapping
    public List<Tool> getAllTools(){
        return toolService.getTools();
    }
    @GetMapping("/code")
    public List<Tool> getToolsByCode(@RequestParam("code") String code){
        return toolService.getToolsWithCode(code);
    }
    @GetMapping("/{code}")
    public Tool getToolByCode(@PathVariable("code") String code){
        return toolService.getToolByCode(code);
    }
    @PostMapping("/add")
    public Tool addTool(@RequestBody Tool tool){
        return toolService.addTool(tool);
    }
    @PutMapping("/update")
    public Tool updateTool(@RequestBody Tool tool){
        return toolService.updateTool(tool);
    }
}
