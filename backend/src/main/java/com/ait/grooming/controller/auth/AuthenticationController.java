package com.ait.grooming.controller.auth;

import com.ait.grooming.service.UserService;
import com.ait.grooming.service.exceptions.IsAlreadyExistException;
import com.ait.grooming.utils.AuthHelper;
import com.ait.grooming.utils.request.auth.AuthenticationRequest;
import com.ait.grooming.utils.request.auth.AuthenticationResponse;
import com.ait.grooming.utils.request.auth.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Auth API", description = "Operations for authentication & registration user")
public class AuthenticationController {

    private final UserService userService;
    private final AuthHelper helper;

    @PostMapping("/login")
    @Operation(description = "Authenticate user")
    public ResponseEntity<AuthenticationResponse> authenticationManager(
            @Valid @RequestBody AuthenticationRequest authRequest) {
        return new ResponseEntity<>(
                helper.generateAuthResponse(authRequest.getEmail(), authRequest.getPassword()),
                HttpStatus.OK);
    }

    @PostMapping("/register")
    @Operation(description = "Register new User")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        if (userService.register(request)) {
            return new ResponseEntity<>(
                    helper.generateAuthResponse(request.getEmail(), request.getPassword()),
                    HttpStatus.CREATED);
        }
        throw new IsAlreadyExistException("User with this email is already exist");
    }
}
