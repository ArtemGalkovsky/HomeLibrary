package me.artemgalkovsky.home_library.auth.services;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import me.artemgalkovsky.home_library.auth.controllers.schemas.LoginCredentials;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class JwtAccessTokenService {
    @Value("${jwt.access.signing-key}")
    private String jwtAccessTokenSigningKey;

    @Value("${jwt.access.expiration-seconds}")
    private int jwtAccessTokenExpirationSeconds;

    private SecretKey acessTokenSecretKey;

    @PostConstruct
    public void init() {
        acessTokenSecretKey = Keys.hmacShaKeyFor(jwtAccessTokenSigningKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateJwtAccessToken(LoginCredentials loginCredentials) {
        Date now = Calendar.getInstance().getTime();

        return Jwts.builder()
                .subject(loginCredentials.getEmail())
                .issuedAt(now)
                .expiration(DateUtils.addSeconds(now, jwtAccessTokenExpirationSeconds))
                .signWith(acessTokenSecretKey)
                .compact();
    }

    public Optional<String> getEmailFromJwtToken(String token) {
        try {
            return Optional.of(
                    Jwts.parser()
                            .verifyWith(acessTokenSecretKey)
                            .build()

                            .parseSignedClaims(token)
                            .getPayload()
                            .getSubject());
        } catch (JwtException | IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
