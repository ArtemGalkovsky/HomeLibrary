package me.artemgalkovsky.home_library.auth.repository.entities.auth_related;

import jakarta.persistence.*;
import lombok.Data;
import me.artemgalkovsky.home_library.auth.repository.entities.user_related.User;

@Entity
@Data
@Table(name = "user_auth_providers_table", schema = "users_data")
public class UserAuthProvidersTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id", nullable = false)
    private AuthProvider provider;

    @Column(nullable = false)
    private boolean used;
}
