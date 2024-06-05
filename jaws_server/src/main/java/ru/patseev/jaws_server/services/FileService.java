package ru.patseev.jaws_server.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void save(MultipartFile file, Long ownerId);
    void deleteFile(String name);
}
