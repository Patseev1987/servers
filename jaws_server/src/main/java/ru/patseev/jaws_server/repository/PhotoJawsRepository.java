package ru.patseev.jaws_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.patseev.jaws_server.domain.PhotoJaws;


import java.util.List;

public interface PhotoJawsRepository extends JpaRepository<PhotoJaws, Long> {

    @Query("from PhotoJaws photo where photo.jaw.id = :ownerId")
    List<PhotoJaws> findAllByOwnerId(Long ownerId);


    @Modifying
    @Query("delete from PhotoJaws  photo where photo.fileName = :fileName")
    void deleteByFileName(String fileName);

}
