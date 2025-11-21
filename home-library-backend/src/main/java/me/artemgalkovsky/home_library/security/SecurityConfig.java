package me.artemgalkovsky.home_library.security;

import lombok.*;

@Builder
@NoArgsConstructor
@ToString
public class SecurityConfig {

    @Getter
    private boolean setSecureToSecurityCookies;

    public SecurityConfig(boolean setSecureToSecurityCookies) {
        this.setSecureToSecurityCookies = setSecureToSecurityCookies;
    }
}
