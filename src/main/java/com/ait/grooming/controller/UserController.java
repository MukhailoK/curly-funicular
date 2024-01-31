package com.ait.grooming.controller;

import com.ait.grooming.dto.response.Response;
import com.ait.grooming.dto.user.UserDto;
import com.ait.grooming.service.UserService;
import com.ait.grooming.utils.request.ChangePasswordRequest;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
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

    @PatchMapping
    public ResponseEntity<Response> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser) {
        return service.changePassword(request, connectedUser);
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
    public ResponseEntity<Response> delete(Principal connectedUser) {
        return service.delete(connectedUser);
    }
}