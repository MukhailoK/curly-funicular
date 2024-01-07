package com.ait.grooming.controller;

import com.ait.grooming.dto.pet.PetRequestDto;
import com.ait.grooming.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping("/new")
    public void createPet(@RequestBody PetRequestDto requestDto) {
        petService.createPet(requestDto);
    }
}