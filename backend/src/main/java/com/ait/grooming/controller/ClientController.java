package com.ait.grooming.controller;

import com.ait.grooming.model.Pet;
import com.ait.grooming.model.User;
import com.ait.grooming.service.ClientService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@Data
@RestController
@RequestMapping("/api-client")
public class ClientController {

    private final ClientService service;

    @GetMapping("/user/{user_id}/my_pets")
    public ResponseEntity<List<Pet>> getPets(@PathVariable Integer user_id, @AuthenticationPrincipal Principal connectedUser) {

        return ResponseEntity.ok(service.getAllPetByClientId(user_id, connectedUser));
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
