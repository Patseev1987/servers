package ru.patseev.securityauthserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.patseev.securityauthserver.dto.Jaw;
import ru.patseev.securityauthserver.dto.JawDTO;
import ru.patseev.securityauthserver.service.JawsService;

import java.util.List;

@RestController
@RequestMapping("/jaws")
@RequiredArgsConstructor
public class JawController {
    private final JawsService jawService;

    @GetMapping
    //get all jaws
    public List<JawDTO> getStorageRecords() {
        return jawService.getStorageRecords();
    }

    @PostMapping("/add")
    //add jaw
    public Jaw addJaw(@RequestBody Jaw jaw) {
        return jawService.addJaw(jaw);
    }

    @PutMapping("/update")
    //update jaw
    public Jaw updateJaw(@RequestBody Jaw jaw) {
        return jawService.updateJaw(jaw);
    }

    @DeleteMapping("/delete/{jawId}")
    //delete jaw by id
    public void deleteJaw(@PathVariable Long jawId) {
        jawService.deleteJaw(jawId);
    }
}
