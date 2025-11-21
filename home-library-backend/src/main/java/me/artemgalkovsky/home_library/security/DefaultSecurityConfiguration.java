package me.artemgalkovsky.home_library.security;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.SecureRandom;

@Configuration
public class DefaultSecurityConfiguration {
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> {
           requests.anyRequest().permitAll();
        });
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public SecureRandom secureRandom() {
        return new SecureRandom();
    }

    @Bean
    public SecurityConfig setSecureToSecurityCookies() {
        return SecurityConfig.builder()
                .setSecureToSecurityCookies(true)
                .build();
    }
}
