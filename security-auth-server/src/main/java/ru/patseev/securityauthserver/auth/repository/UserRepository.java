package ru.patseev.securityauthserver.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.patseev.securityauthserver.auth.domain.User;


import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
