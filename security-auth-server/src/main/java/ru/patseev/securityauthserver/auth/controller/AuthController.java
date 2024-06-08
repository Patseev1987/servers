package ru.patseev.securityauthserver.auth.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.patseev.securityauthserver.auth.dto.UserDTO;
import ru.patseev.securityauthserver.auth.services.JwtTokenService;
import ru.patseev.securityauthserver.auth.services.LoginService;


@RestController()
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenService jwtTokenService;
    private final LoginService loginService;
    private final String ROLE_USER = "USER";
    private final String TOKEN_PREFIX = "Bearer ";

    @PostMapping("/sing-in")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        var user = loginService.login(userDTO).orElse(null);
        if (user == null ) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(TOKEN_PREFIX + jwtTokenService.generateToken(user,ROLE_USER));
    }

    @PostMapping("/sing-up")
    public ResponseEntity<String> singUp(@RequestBody UserDTO userDTO) {
        var newUser = loginService.register(userDTO);
        return ResponseEntity.ok(
                TOKEN_PREFIX + jwtTokenService.generateToken(newUser, ROLE_USER));
    }

}
