package ru.patseev.jaws_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.patseev.jaws_server.services.FileService;


import java.io.File;

@RestController
@RequestMapping("/jaws/photo")
@RequiredArgsConstructor
public class PhotoFileController {
    private final FileService fileService;

    //upload the photo to server
    @PostMapping("/upload/{ownerId}")
    public void uploadPhoto(@RequestParam("file") MultipartFile file, @PathVariable Long ownerId) {
        fileService.save(file, ownerId);
    }
    //delete photo by file name
    @DeleteMapping("/delete")
    public void deletePhoto(@RequestParam("fileName") String fileName) {
        fileService.deleteFile(fileName);
    }
}
