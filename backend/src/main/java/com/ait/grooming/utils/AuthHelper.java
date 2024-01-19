package com.ait.grooming.utils;

import com.ait.grooming.service.auth.JwtTokenProvider;
import com.ait.grooming.utils.request.auth.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthHelper {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;


    public AuthenticationResponse generateAuthResponse(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(email);
        return new AuthenticationResponse(jwt);
    }
}
