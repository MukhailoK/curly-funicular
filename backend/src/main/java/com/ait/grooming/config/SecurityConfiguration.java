package com.ait.grooming.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.ait.grooming.model.Role.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private static final String[] WHITE_LIST_URL = {"/api/v1/auth/**",
            "/api/review/**",
            "/api/grooming/**"};
    private final JwtAuthenticationFilter jwtAuthFilter;
//    private final AuthenticationProvider authenticationProvider;
//    private final LogoutHandler logoutHandler;
    private final ApplicationConfig applicationConfig;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(req ->
//                                req.requestMatchers(WHITE_LIST_URL)
//                                        .permitAll()
//                                        .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MASTER.name())
////                                        .requestMatchers(PATCH, "/api/v1/users/**").hasAnyAuthority(CLIENT.name(), ADMIN.name())
////                                        .requestMatchers(PATCH, "/api/v1/users/**").hasAnyRole(CLIENT.name(), ADMIN.name())
////                                        .requestMatchers(PATCH, "/api/v1/users/**").hasAnyAuthority("ROLE_" + CLIENT.name(),"ROLE_" +  ADMIN.name())
//                                        .requestMatchers(PATCH, "/api/v1/users/**").hasAnyAuthority(CLIENT.name(), ADMIN.name())
//                                        .requestMatchers(POST, "/api-client/**").hasAnyRole(CLIENT.name())
//                                        .requestMatchers(GET, "/api-client/**").hasAnyRole(CLIENT.name())
//                                        .requestMatchers(GET, "/api-client/all").hasAnyRole(ADMIN.name())
////                                .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
////                                .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())
//                                        .anyRequest()
//                                        .authenticated()
//                )
//                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//                .logout(logout ->
//                        logout.logoutUrl("/api/v1/auth/logout")
//                                .addLogoutHandler(logoutHandler)
//                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
//                )
//        ;
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> applicationConfig.corsConfigurationSource())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                                req.requestMatchers(WHITE_LIST_URL)
                                        .permitAll()
                                        .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MASTER.name())
//                                        .requestMatchers(PATCH, "/api/v1/users/**").hasAnyAuthority(CLIENT.name(), ADMIN.name())
//                                        .requestMatchers(PATCH, "/api/v1/users/**").hasAnyRole(CLIENT.name(), ADMIN.name())
//                                        .requestMatchers(PATCH, "/api/v1/users/**").hasAnyAuthority("ROLE_" + CLIENT.name(),"ROLE_" +  ADMIN.name())
                                        .requestMatchers(PATCH, "/api/v1/users/**").hasAnyAuthority("CLIENT", "ADMIN")
                                        .requestMatchers(POST, "/api-client/**").hasAnyRole(CLIENT.name())
                                        .requestMatchers(GET, "/api-client/**").hasAnyRole(CLIENT.name())
                                        .requestMatchers(GET, "/api-client/all").hasAnyRole(ADMIN.name())
//                                .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
//                                .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())
                                        .anyRequest()
                                        .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

