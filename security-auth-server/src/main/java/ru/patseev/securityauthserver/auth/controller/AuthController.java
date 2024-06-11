package ru.patseev.securityauthserver.auth.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.patseev.securityauthserver.auth.dto.JwtTokenResponse;
import ru.patseev.securityauthserver.auth.dto.UserDTOForSingIn;
import ru.patseev.securityauthserver.auth.dto.UserDTOForSingUp;
import ru.patseev.securityauthserver.auth.services.JwtTokenService;
import ru.patseev.securityauthserver.auth.services.LoginService;
import ru.patseev.securityauthserver.dto.Worker;
import ru.patseev.securityauthserver.service.WorkerService;


@RestController()
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenService jwtTokenService;
    private final LoginService loginService;
    private final WorkerService workerService;
    private final String ROLE_USER = "USER";
    private final String TOKEN_PREFIX = "Bearer ";

    @PostMapping("/sign-in")
    public ResponseEntity<JwtTokenResponse> login(@RequestBody UserDTOForSingIn userDTO) {
        var user = loginService.login(userDTO).orElse(null);
        if (user == null ) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(new JwtTokenResponse(TOKEN_PREFIX + jwtTokenService.generateToken(user,ROLE_USER)));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<JwtTokenResponse> singUp(@RequestBody UserDTOForSingUp userDTO) {
        var userDTOForSingIn = new UserDTOForSingIn(
                userDTO.username(), userDTO.password()
        );
        var newUser = loginService.register(userDTOForSingIn);
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
        return ResponseEntity.ok(
               new JwtTokenResponse(TOKEN_PREFIX + jwtTokenService.generateToken(newUser, ROLE_USER)));
    }
}
