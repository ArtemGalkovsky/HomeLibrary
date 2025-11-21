package me.artemgalkovsky.home_library.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevelopmentProfileSecurityConfiguration {
    @Bean
    public SecurityConfig setSecureToSecurityCookies() {
        return SecurityConfig.builder()
                .setSecureToSecurityCookies(false)
                .build();
    }
}
