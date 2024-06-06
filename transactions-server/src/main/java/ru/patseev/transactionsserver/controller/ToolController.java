package ru.patseev.transactionsserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.patseev.transactionsserver.domain.Tool;
import ru.patseev.transactionsserver.service.ToolService;

import java.util.List;

@RestController
@RequestMapping("/transaction/tools")
@RequiredArgsConstructor
public class ToolController {
    private final ToolService toolService;
    //get tools
    @GetMapping
    public List<Tool> getAllTools() {
        return toolService.getAllTools();
    }
    //add tool
    @PostMapping("/add")
    public Tool addTool(@RequestBody Tool tool) {
        return toolService.createTool(tool);
    }
    //get tool by code
    @GetMapping("/{code}")
    public Tool getToolByCode(@PathVariable String code) {
        return toolService.getToolByCode(code);
    }
    //update tool
    @PutMapping
    public Tool updateTool(@RequestBody Tool tool) {
        return toolService.createTool(tool);
    }
}
