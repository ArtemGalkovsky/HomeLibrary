package me.artemgalkovsky.home_library.auth.repository.entities.auth_related.providers;

import jakarta.persistence.*;
import lombok.Data;
import me.artemgalkovsky.home_library.auth.repository.entities.user_related.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Data
@Table(name = "local_jwt_provider_users_data", schema = "users_data")
public class LocalJwtProviderUsersData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "access_token", nullable = false)
    private String accessToken;

    @Column(name = "access_token_expiration_timestamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date accessTokenExpiration;

    @Column(name = "access_token_is_valid", nullable = false)
    private boolean accessTokenIsValid;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @Column(name = "refresh_token_expiration_timestamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date refreshTokenExpiration;

    @Column(name = "refresh_token_is_valid", nullable = false)
    private boolean refreshTokenIsValid;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;
}
