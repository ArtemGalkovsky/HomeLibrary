package me.artemgalkovsky.home_library.auth.controllers.schemas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "JWT token response")
public class JwtTokenResponse {

    @Schema(description = "JWT Access token")
    private String accessToken;

    @Schema(description = "JWT Refresh token")
    private String refreshToken;
}
