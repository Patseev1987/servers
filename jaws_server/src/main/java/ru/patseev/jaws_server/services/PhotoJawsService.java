package ru.patseev.jaws_server.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patseev.jaws_server.domain.PhotoJaws;
import ru.patseev.jaws_server.repository.PhotoJawsRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoJawsService {
    private final PhotoJawsRepository photoJawsRepository;

    // save photo to database
    @Transactional
    public void createPhotoJaws(PhotoJaws photoJaws) {
        photoJawsRepository.save(photoJaws);
    }

    // get photos by Jaw_id
    public List<PhotoJaws> getPhotosByOwnerId(Long ownerId) {
        return photoJawsRepository.findAllByOwnerId(ownerId);
    }
    //delete photo by fileName
    @Transactional
    public void deletePhotoJaws(String name) {
        photoJawsRepository.deleteByFileName(name);
    }
}
