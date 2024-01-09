package com.ait.grooming.service;

import com.ait.grooming.dto.pet.PetRequestDto;
import com.ait.grooming.model.Breed;
import com.ait.grooming.model.Pet;
import com.ait.grooming.model.User;
import com.ait.grooming.repository.BreedRepository;
import com.ait.grooming.repository.PetRepository;
import com.ait.grooming.repository.UserRepository;
import com.ait.grooming.service.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final BreedRepository breedRepository;
    private final UserRepository userRepository;

    public boolean createPet(PetRequestDto requestDto) {
        if (!petRepository.existsByName(requestDto.getName())) {
            try {
                Breed breedById = breedRepository.findById(requestDto.getBreed().getId())
                        .orElseThrow(() -> new NotFoundException("Breed with id " + requestDto.getBreed().getId() + " not found!"));

                User userById = userRepository.findById(requestDto.getOwner().getId())
                        .orElseThrow(() -> new NotFoundException("User with id " + requestDto.getOwner().getId() + " not found!"));



                Pet pet = new Pet();
                pet.setName(requestDto.getName());
              //  pet.setOwner(requestDto.getOwner());
                pet.setOwner(userById);
                pet.setType(Pet.PetType.DOG);
              //  pet.setBreed(requestDto.getBreed());
                pet.setBreed(breedById);
                pet.setPhotoUrl(requestDto.getPhotoUrl());
                pet.setSpecial_notes(requestDto.getSpecialNotes());
                petRepository.save(pet);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return false;
    }
}