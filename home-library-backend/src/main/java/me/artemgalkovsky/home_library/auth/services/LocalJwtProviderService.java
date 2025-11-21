package me.artemgalkovsky.home_library.auth.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.artemgalkovsky.home_library.auth.controllers.schemas.LoginCredentials;
import me.artemgalkovsky.home_library.auth.dtos.auth_related.providers.LocalJwtProviderTokensDto;
import me.artemgalkovsky.home_library.auth.exceptions.IncorrectPasswordException;
import me.artemgalkovsky.home_library.auth.exceptions.InvalidRefreshToken;
import me.artemgalkovsky.home_library.auth.exceptions.UserNotFoundException;
import me.artemgalkovsky.home_library.auth.repository.LocalJwtProviderUsersDataRepository;
import me.artemgalkovsky.home_library.auth.repository.UserRepository;
import me.artemgalkovsky.home_library.auth.repository.entities.auth_related.providers.LocalJwtProviderUsersData;
import me.artemgalkovsky.home_library.auth.repository.entities.user_related.User;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
@Getter
@Slf4j
public class LocalJwtProviderService {

    private final JwtAccessTokenService jwtAccessTokenService;
    private final JwtRefreshTokenService jwtRefreshTokenService;
    private final LocalJwtProviderUsersDataRepository localJwtProviderUsersDataRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveTokensToRepository(LoginCredentials loginCredentials, LocalJwtProviderTokensDto localJwtProviderTokensDto) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(loginCredentials.getEmail());

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User with email '" + loginCredentials.getEmail() + "' not found!");
        }

        User user = optionalUser.get();

        LocalJwtProviderUsersData localJwtProviderUsersData = localJwtProviderUsersDataRepository.findByUser(user)
                .orElseGet(() -> {
                    var localJwtProviderUsersDataEntity = new LocalJwtProviderUsersData();

                    localJwtProviderUsersDataEntity.setUser(user);

                    return localJwtProviderUsersDataEntity;
                });

        Date now = Calendar.getInstance().getTime();

        localJwtProviderUsersData.setAccessToken(localJwtProviderTokensDto.getAccessToken());
        localJwtProviderUsersData.setAccessTokenIsValid(true);
        localJwtProviderUsersData.setAccessTokenExpiration(
                DateUtils.addSeconds(
                        now,
                        jwtAccessTokenService.getJwtAccessTokenExpirationSeconds()
                )
        );

        localJwtProviderUsersData.setRefreshToken(localJwtProviderTokensDto.getRefreshToken());
        localJwtProviderUsersData.setRefreshTokenIsValid(true);
        localJwtProviderUsersData.setRefreshTokenExpiration(
                DateUtils.addSeconds(
                        now,
                        jwtRefreshTokenService.getJwtRefreshTokenExpirationSeconds()
                )
        );

        localJwtProviderUsersDataRepository.save(localJwtProviderUsersData);
    }

    // TODO: SET TOKENS UNIQUE CONSTRAINTS IN LOCAL JWT...!

    private LocalJwtProviderTokensDto generateTokensWithEmail(String email) {
        return LocalJwtProviderTokensDto.builder()
                .accessToken(jwtAccessTokenService.generateJwtAccessToken(email))
                .refreshToken(jwtRefreshTokenService.generateJwtRefreshToken())
                .build();
    }

    public LocalJwtProviderTokensDto generateTokens(LoginCredentials loginCredentials) throws IncorrectPasswordException, UserNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(loginCredentials.getEmail());

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User with email '%s' was not found!".formatted(loginCredentials.getEmail()));
        }

        boolean passwordsMatches = passwordEncoder.matches(loginCredentials.getPassword(), optionalUser.get().getPassword());
        if (!passwordsMatches) {
            throw new IncorrectPasswordException("Incorrect password for user with email: " + loginCredentials.getEmail());
        }

        return generateTokensWithEmail(loginCredentials.getEmail());
    }

    public LocalJwtProviderTokensDto generateTokens(String refreshToken) throws InvalidRefreshToken {
        Optional<LocalJwtProviderUsersData> localJwtProviderUsersData = localJwtProviderUsersDataRepository.findByRefreshToken(refreshToken);

        if (localJwtProviderUsersData.isEmpty()) {
            throw new InvalidRefreshToken("Invalid refresh token!");
        }

        return generateTokensWithEmail(localJwtProviderUsersData.get().getUser().getEmail());
    }
}
