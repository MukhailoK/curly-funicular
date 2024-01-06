package com.ait.grooming.controller;

import com.ait.grooming.service.ClientService;
import com.ait.grooming.utils.request.UserRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody UserRegistrationRequest request) {
        return clientService.create(request);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<Object> getByUserName(@PathVariable String userName) {
        return clientService.getUserByUserName(userName);
    }
}
