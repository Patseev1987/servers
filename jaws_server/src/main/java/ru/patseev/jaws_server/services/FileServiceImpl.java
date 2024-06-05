package ru.patseev.jaws_server.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.patseev.jaws_server.domain.PhotoJaws;
import ru.patseev.jaws_server.utils.UploadServerProperty;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final PhotoJawsService photoJawsService;
    private final JawService jawService;
    private final UploadServerProperty uploadServerProperty;

    // save the photo in database
    @Transactional
    @Override
    public void save(MultipartFile file, Long ownerId) {

        String fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss")) + "_" + file.getOriginalFilename();

        String path = getPathToResources();
        try {
            file.transferTo(new File(path + fileName));
            var jaw = jawService.findById(ownerId).orElse(null);
            String url = uploadServerProperty.getServerUrl() + fileName;
            var photo = PhotoJaws.builder()
                    .jaw(jaw)
                    .url(url)
                    .fileName(fileName)
                    .createDate(LocalDateTime.now())
                    .build();
            photoJawsService.createPhotoJaws(photo);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Transactional
    @Override
    public void deleteFile(String name){
        try {
            String path = getPathToResources() + name;
            Files.delete(Path.of(path));
            photoJawsService.deletePhotoJaws(name);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    private String getPathToResources (){
        return new StringBuilder()
                .append(System.getProperty("user.dir"))
                .append(File.separator)
                .append("src")
                .append(File.separator)
                .append("main")
                .append(File.separator)
                .append("resources")
                .append(File.separator)
                .append("static")
                .append(File.separator)
                .append("images")
                .append(File.separator)
                .toString();
    }

}
