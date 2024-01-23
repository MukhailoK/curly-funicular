package com.ait.grooming.controller;

import com.ait.grooming.dto.user.UserDto;
import com.ait.grooming.service.UserService;
import com.ait.grooming.utils.AuthHelper;
import com.ait.grooming.utils.request.ChangePasswordRequest;
import com.ait.grooming.utils.request.auth.AuthenticationResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
@Log4j2
public class UserController {

    private final UserService service;
    private final AuthHelper helper;

    @PatchMapping
    public ResponseEntity<AuthenticationResponse> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return new ResponseEntity<>(helper.generateAuthResponse(connectedUser.getName(), request.getNewPassword()), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAll() {
        return service.getAll();
    }

    @GetMapping("/user-info")
    public ResponseEntity<UserDto> getUserInfo(Principal connectedUser) {
        return service.getUserByPrincipalName(connectedUser.getName());
    }

    @DeleteMapping
    public ResponseEntity<?> delete(Principal connectedUser) {
        return service.delete(connectedUser);
    }
}