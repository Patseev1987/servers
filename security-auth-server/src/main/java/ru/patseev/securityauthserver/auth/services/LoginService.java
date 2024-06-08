package ru.patseev.securityauthserver.auth.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patseev.securityauthserver.auth.domain.User;
import ru.patseev.securityauthserver.auth.dto.UserDTO;


import java.util.Optional;
@Service
@RequiredArgsConstructor
public class LoginService {

   private final UserService userService;



    public Optional<User> login(UserDTO userDTO) {
        User user = userService.getByUsername(userDTO.username());
        if (user != null && userDTO.password().equals(user.getPassword())) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public User register(UserDTO userDTO) {
        userService.create(userDTO);
        return userService.getByUsername(userDTO.username());
    }


}
