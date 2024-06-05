package ru.patseev.jaws_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.patseev.jaws_server.domain.Jaw;

import java.util.List;


public interface JawsRepository extends JpaRepository<Jaw, Long> {
    @Query("from Jaw  jaw where jaw.name like :name")
    List<Jaw> findAllByName(String name);
}
