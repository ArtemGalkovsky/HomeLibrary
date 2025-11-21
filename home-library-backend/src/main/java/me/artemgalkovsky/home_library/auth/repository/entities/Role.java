package me.artemgalkovsky.home_library.auth.repository.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles", schema = "users_data")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String roleName;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
