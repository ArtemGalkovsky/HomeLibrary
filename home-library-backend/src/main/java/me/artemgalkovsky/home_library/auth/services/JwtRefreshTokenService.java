package me.artemgalkovsky.home_library.auth.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class JwtRefreshTokenService {

    @Getter
    @Value("${jwt.refresh.expiration-seconds}")
    private int jwtRefreshTokenExpirationSeconds;

    public String generateJwtRefreshToken() {
        SecureRandom secureRandom = new SecureRandom();

        byte[] randomBytes = new byte[32];
        secureRandom.nextBytes(randomBytes);

        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }
}
