package com.library.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.Customizer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/auth/register", 
                    "/swagger-ui/**", 
                    "/v3/api-docs/**"
                ).permitAll()
                .requestMatchers("/api/auth/registerAdmin").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "api/books").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "api/books/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "api/books/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "api/books/**").authenticated()
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
