package ru.patseev.jaws_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.patseev.jaws_server.domain.Jaw;
import ru.patseev.jaws_server.domain.PhotoJaws;
import ru.patseev.jaws_server.dto.JawDTO;
import ru.patseev.jaws_server.dto.Mapper;
import ru.patseev.jaws_server.services.JawService;
import ru.patseev.jaws_server.services.PhotoJawsService;


import java.util.List;

@RestController
@RequestMapping("/jaws")
@RequiredArgsConstructor
public class JawsRestController {
    private final JawService jawService;
    private final PhotoJawsService photoJawsService;

    //get all jaws with photo links
    @GetMapping
    public List<JawDTO> getAllJaws() {

        return jawService.findAll().stream()
                .map(Mapper::JawToJawDTO)
                .map(it -> {
                    var photoUrls = photoJawsService
                            .getPhotosByOwnerId(it.getId())
                            .stream()
                            .map(PhotoJaws::getUrl)
                            .toList();
                    it.setPhotosUrls(photoUrls);
                    return it;
                })
                .toList();
    }

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

}
