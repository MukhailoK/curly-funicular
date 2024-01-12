package com.ait.grooming.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ait.grooming.model.Permission.*;

@Getter
@RequiredArgsConstructor
public enum Role {

    CLIENT(
            Set.of(
                    CLIENT_CREATE,
                    CLIENT_DELETE,
                    CLIENT_UPDATE,
                    CLIENT_READ

            )),
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE,
                    MASTER_READ,
                    MASTER_UPDATE,
                    MASTER_DELETE,
                    MASTER_CREATE,
                    CLIENT_CREATE,
                    CLIENT_DELETE,
                    CLIENT_UPDATE,
                    CLIENT_READ
            )
    ),
    MASTER(
            Set.of(
                    MASTER_READ,
                    MASTER_UPDATE,
                    MASTER_DELETE,
                    MASTER_CREATE
            )
    );

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}

