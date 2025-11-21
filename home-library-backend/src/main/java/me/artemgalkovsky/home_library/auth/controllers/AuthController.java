package me.artemgalkovsky.home_library.auth.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.artemgalkovsky.home_library.auth.controllers.schemas.JwtTokenResponse;
import me.artemgalkovsky.home_library.auth.controllers.schemas.LoginCredentials;
import me.artemgalkovsky.home_library.auth.dtos.auth_related.providers.LocalJwtProviderTokensDto;
import me.artemgalkovsky.home_library.auth.exceptions.IncorrectPasswordException;
import me.artemgalkovsky.home_library.auth.exceptions.InvalidRefreshToken;
import me.artemgalkovsky.home_library.auth.exceptions.UserNotFoundException;
import me.artemgalkovsky.home_library.auth.services.LocalJwtProviderService;
import me.artemgalkovsky.home_library.security.SecurityConfig;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final LocalJwtProviderService localJwtProviderService;
    private final SecurityConfig securityConfig;

    private ResponseEntity<JwtTokenResponse> generateJwtTokenResponseEntity(LocalJwtProviderTokensDto localJwtProviderTokensDto) {
        ResponseCookie refreshTokenCookie = ResponseCookie.from("refresh-token", localJwtProviderTokensDto.getRefreshToken())
                .httpOnly(true)
                .maxAge(localJwtProviderService.getJwtRefreshTokenService().getJwtRefreshTokenExpirationSeconds())
                .path("/")
                .secure(securityConfig.isSetSecureToSecurityCookies()).
                build();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

        return new ResponseEntity<>(
                new JwtTokenResponse(localJwtProviderTokensDto.getAccessToken()),
                headers,
                HttpStatus.OK
        );
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponse> login(@Valid @RequestBody LoginCredentials loginCredentials) throws UserNotFoundException, IncorrectPasswordException {
        LocalJwtProviderTokensDto localJwtProviderTokensDto = localJwtProviderService.generateTokens(loginCredentials);

        localJwtProviderService.saveTokensToRepository(loginCredentials, localJwtProviderTokensDto);

        return generateJwtTokenResponseEntity(localJwtProviderTokensDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtTokenResponse> refresh(@CookieValue(name = "refresh-token") String refreshToken) throws InvalidRefreshToken {
        return generateJwtTokenResponseEntity(
                localJwtProviderService.generateTokens(refreshToken)
        );
    }

    @GetMapping("/encrypt-string")
    public String encryptString(@RequestParam(name = "s") String string) {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(string);
    }
}
