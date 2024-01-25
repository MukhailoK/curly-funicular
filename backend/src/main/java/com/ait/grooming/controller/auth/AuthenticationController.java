package com.ait.grooming.controller.auth;

import com.ait.grooming.service.UserService;
import com.ait.grooming.utils.AuthHelper;
import com.ait.grooming.utils.request.auth.AuthenticationRequest;
import com.ait.grooming.utils.request.auth.AuthenticationResponse;
import com.ait.grooming.utils.request.auth.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.xml.bind.DataBindingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
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
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }


}
