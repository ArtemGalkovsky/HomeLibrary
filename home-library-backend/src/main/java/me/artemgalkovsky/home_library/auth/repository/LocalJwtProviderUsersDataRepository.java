package me.artemgalkovsky.home_library.auth.repository;

import me.artemgalkovsky.home_library.auth.repository.entities.auth_related.providers.LocalJwtProviderUsersData;
import me.artemgalkovsky.home_library.auth.repository.entities.user_related.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface LocalJwtProviderUsersDataRepository extends JpaRepository<LocalJwtProviderUsersData, Long> {

    Optional<LocalJwtProviderUsersData> findByAccessToken(String accessToken);

    Optional<LocalJwtProviderUsersData> findByRefreshToken(String refreshToken);

    Optional<LocalJwtProviderUsersData> findByUserId(Long userId);

    Optional<LocalJwtProviderUsersData> findByUser(User user);
}
