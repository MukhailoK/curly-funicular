package com.ait.grooming.controller;

import com.ait.grooming.dto.pet.PetDto;
import com.ait.grooming.model.Breed;
import com.ait.grooming.model.Pet;
import com.ait.grooming.utils.request.PetRequest;
import com.ait.grooming.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor

public class PetController {

    private final PetService petService;

    @PostMapping
    public ResponseEntity<PetDto> createPet(@RequestBody PetRequest requestDto, Principal connectedUser) {
       return petService.createPet(requestDto, connectedUser);
    }

    @GetMapping("/breeds")
    public ResponseEntity<List<Breed>> getAllBreeds(){
        return petService.getAllBreed();
    }

//    @GetMapping("/types")
//    public ResponseEntity<?> getAllTypes(){
//        return petService.getAllTypes();
//    }
    @GetMapping("/findByName/{petName}")
    public ResponseEntity<PetDto> getPetByName(@PathVariable String petName, Principal connectedUser){
        return petService.findByPetName(petName, connectedUser);
    }


}