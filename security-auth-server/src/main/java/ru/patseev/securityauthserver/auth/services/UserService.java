package ru.patseev.securityauthserver.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.patseev.securityauthserver.auth.domain.Role;
import ru.patseev.securityauthserver.auth.domain.User;
import ru.patseev.securityauthserver.auth.dto.UserDTOForSingIn;
import ru.patseev.securityauthserver.auth.dto.UserDTOForUpdate;
import ru.patseev.securityauthserver.auth.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    /**
     * Сохранение пользователя
     *
     * @return сохраненный пользователь
     */
    public User save(User user) {
        return userRepository.save(user);
    }


    /**
     * Создание пользователя
     *
     * @return созданный пользователь
     */
    public User create(UserDTOForSingIn userDTO) {
        if (userRepository.existsByUsername(userDTO.username())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }
        var user = new User();
        user.setUsername(userDTO.username());
        user.setPassword(userDTO.password());
        user.setRole(Role.USER);
        return save(user);
    }

    public void update(UserDTOForUpdate userDTO) {
        var user = userRepository.findByUsername(userDTO.username()).orElseThrow();
        user.setPassword(userDTO.newPassword());
        userRepository.save(user);
    }


    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    public User getByUsername(String username) {
      return userRepository.findByUsername(username)
                .orElse(null);
    }
    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }
    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }
}
