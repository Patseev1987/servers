package ru.patseev.securityauthserver.auth.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patseev.securityauthserver.auth.domain.User;
import ru.patseev.securityauthserver.auth.dto.UserDTOForSingIn;
import ru.patseev.securityauthserver.auth.dto.UserDTOForSingUp;
import ru.patseev.securityauthserver.auth.dto.UserDTOForUpdate;
import ru.patseev.securityauthserver.dto.Worker;
import ru.patseev.securityauthserver.service.WorkerService;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserService userService;
    private final WorkerService workerService;

    //login old user
    public Optional<User> login(UserDTOForSingIn userDTO) {
        User user = userService.getByUsername(userDTO.username());
        if (user != null && userDTO.password().equals(user.getPassword())) {
            return Optional.of(user);
        }
        return Optional.empty();
    }
    //register new user
    public User register(UserDTOForSingUp userDTO) {
        var userDTOForSingIn = new UserDTOForSingIn(
                userDTO.username(), userDTO.password()
        );
        var newUser = userService.create(userDTOForSingIn);
        var worker = Worker.builder()
                .id(newUser.getId())
                .login(newUser.getUsername())
                .firstName(userDTO.firstName())
                .lastName(userDTO.lastName())
                .department(userDTO.department())
                .patronymic(userDTO.patronymic())
                .type(userDTO.type())
                .joinDate(userDTO.joinDate())
                .build();
        workerService.addWorker(worker);
        return userService.getByUsername(userDTO.username());
    }

    //change password
    public void updatePassword(UserDTOForUpdate userDTO) {
        var user = userService.getByUsername(userDTO.username());
        if (user == null){
            throw new IllegalArgumentException("User not found");
        }
        if (user.getPassword().equals(userDTO.oldPassword())){
            user.setPassword(userDTO.newPassword());
        }
        userService.update(userDTO);
    }
}

