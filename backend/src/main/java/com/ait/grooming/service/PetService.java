package com.ait.grooming.service;

import com.ait.grooming.dto.pet.PetRequestDto;
import com.ait.grooming.model.Pet;
import com.ait.grooming.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    public boolean createPet(PetRequestDto requestDto) {
        if (!petRepository.existsByName(requestDto.getName())) {
            try {
                Pet pet = new Pet();
                pet.setName(requestDto.getName());
                pet.setOwner(requestDto.getOwner());
                pet.setType(requestDto.getType());
                pet.setBreed(requestDto.getBreed());
                pet.setPhotoUrl(requestDto.getPhotoUrl());
                pet.setSpecialNotes(requestDto.getSpecialNotes());
                petRepository.save(pet);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return false;
    }
}