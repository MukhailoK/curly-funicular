package com.ait.grooming.config;

import com.ait.grooming.model.Role;
import com.ait.grooming.model.User;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@TestConfiguration
@Profile("test")
public class TestConfig {

    public static final String MOCK_USER = "client1@example.com";
    public static final String MOCK_ADMIN = "client2@example.com";
    public static final String MOCK_DELETE_USER = "client3@example.com";
    public static final String PASSWORD = "$2a$10$RwUhI4UxVEVRMxjrWKRa4eIIM.KOUqZNgYuBorTDEIGtu45lvc5lm";

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return switch (username) {
                    case MOCK_USER -> User.builder()
                            .id(1)
                            .email(MOCK_USER)
                            .password(PASSWORD)
                            .role(Role.CLIENT)
                            .build();
                    case MOCK_ADMIN -> User.builder()
                            .id(1)
                            .email(MOCK_ADMIN)
                            .password(PASSWORD)
                            .role(Role.ADMIN)
                            .build();
                    case MOCK_DELETE_USER -> User.builder()
                            .id(1)
                            .email(MOCK_DELETE_USER)
                            .password(PASSWORD)
                            .role(Role.CLIENT)
                            .build();
                    default -> throw new UsernameNotFoundException("User not found");
                };
            }
        };
    }
}
