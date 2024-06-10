package ru.patseev.securityauthserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    public List<JawDTO> getJaws() {
        return jawService.getJaws();
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

    @PostMapping("/photo/{jawId}")
    //upload photo to server
    public void addPhoto(@RequestParam("file") MultipartFile multipartFile, @PathVariable Long jawId) {
        jawService.addPhoto(multipartFile, jawId);
    }

    @DeleteMapping("photo/delete")
    //delete photo by file name
    public void deletePhoto(@RequestParam String fileName) {
        jawService.deletePhoto(fileName);
    }
}
