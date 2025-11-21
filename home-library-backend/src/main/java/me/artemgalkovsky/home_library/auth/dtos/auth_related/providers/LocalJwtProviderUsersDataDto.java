package me.artemgalkovsky.home_library.auth.dtos.auth_related.providers;

import me.artemgalkovsky.home_library.auth.dtos.user_related.UserDto;

import java.util.Date;

public class LocalJwtProviderUsersDataDto {

    private UserDto user;

    private String accessToken;
    private Date accessTokenExpiration;
    private Boolean accessTokenIsValid;

    private String refreshToken;
    private Date refreshTokenExpiration;
    private Boolean refreshTokenIsValid;

    private Date updatedAt;
    private Date createdAt;
}
