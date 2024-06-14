package ru.patseev.securityauthserver.auth.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.patseev.securityauthserver.auth.dto.JwtTokenResponse;
import ru.patseev.securityauthserver.auth.dto.UserDTOForSingIn;
import ru.patseev.securityauthserver.auth.dto.UserDTOForSingUp;
import ru.patseev.securityauthserver.auth.dto.UserDTOForUpdate;
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
    private final String ROLE_USER = "USER";
    private final String TOKEN_PREFIX = "Bearer ";

    //login
    @PostMapping("/sign-in")
    public ResponseEntity<JwtTokenResponse> login(@RequestBody UserDTOForSingIn userDTO) {
        var user = loginService.login(userDTO).orElse(null);
        if (user == null ) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(new JwtTokenResponse(TOKEN_PREFIX + jwtTokenService.generateToken(user,ROLE_USER)));
    }

    //register new user
    @PostMapping("/sign-up")
    public ResponseEntity<JwtTokenResponse> singUp(@RequestBody UserDTOForSingUp userDTO) {
        var newUser = loginService.register(userDTO);
        return ResponseEntity.ok(
               new JwtTokenResponse(TOKEN_PREFIX + jwtTokenService.generateToken(newUser, ROLE_USER)));
    }

    //change password
    @PutMapping("/update")
    public ResponseEntity<String> updatePassword(@RequestBody UserDTOForUpdate userDTO) {
        loginService.updatePassword(userDTO);
        return ResponseEntity.ok("Password updated");
    }
}
