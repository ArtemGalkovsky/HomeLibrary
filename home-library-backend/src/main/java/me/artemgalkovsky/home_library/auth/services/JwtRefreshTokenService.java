package me.artemgalkovsky.home_library.auth.services;

import org.springframework.beans.factory.annotation.Value;

import java.security.SecureRandom;
import java.util.Base64;

public class JwtRefreshTokenService {

    @Value("${jwt.refresh.expiration-seconds")
    private int jwtRefreshTokenExpirationSeconds;

    public String generateJwtRefreshToken() {
        SecureRandom secureRandom = new SecureRandom();

        byte[] randomBytes = new byte[256];
        secureRandom.nextBytes(randomBytes);

        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }
}
