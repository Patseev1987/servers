package ru.patseev.securityauthserver.auth.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.patseev.securityauthserver.auth.services.JwtTokenService;


@RestController()
public class TestController {

    private final JwtTokenService service;


    public TestController(JwtTokenService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> testEndpoint(HttpServletRequest httpServletRequest){


        return new ResponseEntity<>( "Hello World! asdasdasdasdasdasdasdasdasd" , HttpStatus.OK);
    }

}
