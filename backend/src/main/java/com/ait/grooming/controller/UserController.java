package com.ait.grooming.controller;

import com.ait.grooming.dto.user.UserDto;
import com.ait.grooming.service.UserService;
import com.ait.grooming.utils.request.ChangePasswordRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Log4j2
public class UserController {

    private final UserService service;

    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {

        return service.changePassword(request, connectedUser);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAll(){
        return service.getAll();
    }


}