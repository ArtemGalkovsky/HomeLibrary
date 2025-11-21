package me.artemgalkovsky.home_library.auth.repository;

import me.artemgalkovsky.home_library.auth.repository.entities.auth_related.AuthProvider;
import me.artemgalkovsky.home_library.auth.repository.entities.auth_related.UserAuthProvidersTable;
import me.artemgalkovsky.home_library.auth.repository.entities.user_related.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAuthProvidersTableRepository extends JpaRepository<UserAuthProvidersTable, Long> {
    Optional<UserAuthProvidersTable> findByUser(User user);
}
