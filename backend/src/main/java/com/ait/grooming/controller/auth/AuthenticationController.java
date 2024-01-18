package com.ait.grooming.controller.auth;

import com.ait.grooming.service.UserService;
import com.ait.grooming.service.auth.JwtTokenProvider;
import com.ait.grooming.utils.request.auth.AuthenticationRequest;
import com.ait.grooming.utils.request.auth.AuthenticationResponse;
import com.ait.grooming.utils.request.auth.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Auth API", description = "Operations for authentication & registration user")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;

    @PostMapping("/login")
    @Operation(description = "Login")
    public ResponseEntity<AuthenticationResponse> authenticationManager(@Valid @RequestBody AuthenticationRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authRequest.getEmail());

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/register")
    @Operation(description = "Register new User")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        if (userService.register(request)) {
            return authenticationManager(new AuthenticationRequest(request.getEmail(), request.getPassword()));
        }
        return ResponseEntity.badRequest().build();
    }
}
