package me.artemgalkovsky.home_library.auth.controllers;

import me.artemgalkovsky.home_library.auth.controllers.schemas.JwtTokenResponse;
import me.artemgalkovsky.home_library.auth.controllers.schemas.LoginCredentials;
import me.artemgalkovsky.home_library.auth.services.JwtAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtAccessTokenService jwtAccessTokenService;

    @Autowired
    public AuthController(JwtAccessTokenService jwtAccessTokenService) {
        this.jwtAccessTokenService = jwtAccessTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponse> login(@RequestBody LoginCredentials loginCredentials) {
        String accessToken = jwtAccessTokenService.generateJwtAccessToken(loginCredentials);

        return new ResponseEntity<>(
                JwtTokenResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken("1234")
                        .build(),
                HttpStatus.OK
        );
    }
}
