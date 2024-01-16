package com.ait.grooming.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
@Getter
@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    CLIENT,
    ADMIN,
    MASTER;

    @Override
    public String getAuthority() {
        return this.name().toUpperCase();
    }

}

