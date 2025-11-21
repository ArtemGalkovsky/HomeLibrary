package me.artemgalkovsky.home_library.auth.controllers.schemas;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Login request body")
public class LoginCredentials {

    @Schema(description = "User's email", example = "email@example.com")
    @Size(min=5, max=254)
    @NotEmpty
    @NotNull
    @Email
    private final String email;

    @Schema(description = "User's password", example = "12345")
    @Size(min=5, max=255)
    @NotEmpty
    @NotNull
    private final String password;
}
