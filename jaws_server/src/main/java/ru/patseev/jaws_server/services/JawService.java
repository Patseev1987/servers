package ru.patseev.jaws_server.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patseev.jaws_server.domain.Jaw;
import ru.patseev.jaws_server.repository.JawsRepository;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JawService {
    private final JawsRepository jawsRepository;

    // save jaw to database
    @Transactional
    public Jaw save(Jaw jaw) {
        return jawsRepository.save(jaw);
    }

    // get all jaws
    public List<Jaw> findAll() {
        return jawsRepository.findAll();
    }

    // get jaw by id
    public Optional<Jaw> findById(Long id) {
        return jawsRepository.findById(id);
    }

    // delete jaw by id
    @Transactional
    public void delete(Long id) {
        jawsRepository.deleteById(id);
    }
}
