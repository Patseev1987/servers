package ru.patseev.jaws_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.patseev.jaws_server.domain.Jaw;
import ru.patseev.jaws_server.dto.JawDTO;
import ru.patseev.jaws_server.utils.Mapper;
import ru.patseev.jaws_server.services.JawService;
import ru.patseev.jaws_server.services.PhotoJawsService;


import java.util.List;

@RestController
@RequestMapping("/jaws")
@RequiredArgsConstructor
public class JawsRestController {
    private final JawService jawService;
    private final PhotoJawsService photoJawsService;
    //add jaw to database
    @PostMapping("/add")
    public Jaw createJaw(@RequestBody Jaw jaw) {
        return jawService.save(jaw);
    }
    //update jaw in database
    @PutMapping("update")
    public Jaw updateJaw(@RequestBody Jaw jaw) {
        return jawService.save(jaw);
    }
    //delete jaw by id
    @DeleteMapping("/delete/{id}")
    void deleteJaw(@PathVariable Long id) {
        jawService.delete(id);
    }
    //get all jaws with photo links
    @GetMapping
    public List<JawDTO> getAllJaws() {
        return jawService.findAll().stream()
                .map(Mapper::JawToJawDTO2)
                .peek(jaw -> {
                    var photos = photoJawsService
                            .getPhotosByOwnerId(jaw.getId())
                            .stream()
                            .map(Mapper::toPhotoJawsDTO)
                            .toList();
                    jaw.setPhotos(photos);
                })
                .toList();
    }
}
