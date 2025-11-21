package me.artemgalkovsky.home_library.auth.repository;

import me.artemgalkovsky.home_library.auth.repository.entities.auth_related.AuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthProvidersRepository extends JpaRepository<AuthProvider, Integer> {
}
