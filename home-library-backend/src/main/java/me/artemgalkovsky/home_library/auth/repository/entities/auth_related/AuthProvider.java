package me.artemgalkovsky.home_library.auth.repository.entities.auth_related;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "auth_providers", schema = "users_data")
public class AuthProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "provider", nullable = false, unique = true)
    private String providerName;
}
