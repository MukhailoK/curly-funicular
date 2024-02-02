package com.ait.grooming.controller;

import com.ait.grooming.dto.pet.PetDto;
import com.ait.grooming.dto.response.ErrorResponse;
import com.ait.grooming.model.Breed;
import com.ait.grooming.service.PetService;
import com.ait.grooming.utils.request.PetRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/pets")
@AllArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping
    public ResponseEntity<PetDto> createPet(@RequestBody PetRequest request, Principal connectedUser) {
        return petService.createPet(request, connectedUser);
    }

    @GetMapping("/breeds")
    public ResponseEntity<List<Breed>> getAllBreeds() {
        return petService.getAllBreed();
    }

    @GetMapping("/{petName}")
    public ResponseEntity<PetDto> getPetByName(@PathVariable String petName, Principal connectedUser) {
        return petService.findByPetName(petName, connectedUser);
    }

    @GetMapping("/all/{petName}")
    public ResponseEntity<List<PetDto>> getAllPetByName(@PathVariable String petName) {
        return petService.findAllByPetName(petName);
    }

    @DeleteMapping("{petName}")
    public ResponseEntity<ErrorResponse> delete(@PathVariable String petName, Principal principal){
        return petService.delete(petName, principal);
    }

}