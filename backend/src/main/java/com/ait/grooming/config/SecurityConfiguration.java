package com.ait.grooming.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.ait.grooming.model.Role.ADMIN;
import static com.ait.grooming.model.Role.CLIENT;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {


    private static final String[] WHITE_LIST_URL = {"/api/v1/auth/**",
            "/api/review/**",
            "/api/grooming/**",
            "/api/pets/breeds",
            "/api/pets/types",
            "/api/booking/**",
            "/openapi/v1/appointments/**",
            "/api/v1/mail_sender",

            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/api-docs/swagger-config"};

    private static final String[] SECURED_LIST_URL = {
            "/api/v1/appointments/**",
            "/api/v1/users/**",
            "/api/pets/new",
            "/api/pets/findByName/**"
    };

    private static final String[] ACTUATOR_LIST_URL = {
            "/actuator/health",
            "/actuator/metrics"
    };
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final ApplicationConfig applicationConfig;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> applicationConfig.corsConfigurationSource())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers(SECURED_LIST_URL).hasAnyAuthority(ADMIN.name(), CLIENT.name())
                                .requestMatchers(ACTUATOR_LIST_URL).hasAnyAuthority(ADMIN.name())
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/v1/auth/logout")
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()));
        return http.build();
    }
}