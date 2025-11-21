package me.artemgalkovsky.home_library.auth.dtos.auth_related.providers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocalJwtProviderTokensDto {
    private String accessToken;
    private String refreshToken;
}
