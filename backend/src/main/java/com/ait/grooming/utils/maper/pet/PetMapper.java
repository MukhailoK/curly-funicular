package com.ait.grooming.utils.maper.pet;

import com.ait.grooming.dto.pet.PetDto;
import com.ait.grooming.model.Pet;
import com.ait.grooming.repository.BreedRepository;
import com.ait.grooming.repository.UserRepository;
import com.ait.grooming.service.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class PetMapper {

    private final BreedRepository breedRepository;
    private final UserRepository userRepository;

    public static PetDto toPetDto(Pet pet) {
        PetDto petDto = new PetDto();
        petDto.setName(pet.getName());
        petDto.setOwnerEmail(pet.getOwner().getEmail());
//        petDto.setType(pet.getType().name());
        petDto.setBreed(pet.getBreed().getName());
//        petDto.setPhotoUrl(pet.getPhotoUrl());
        petDto.setSpecialNotes(pet.getSpecial_notes());
        return petDto;
    }

    public static List<PetDto> allToPetDto(List<Pet> pets) {
        return pets.stream().map(PetMapper::toPetDto).toList();
    }

    public Pet toEntity(PetDto petDto) {
        Pet pet = new Pet();
        pet.setName(petDto.getName());
        pet.setBreed(breedRepository.findByName(petDto.getBreed()));
        pet.setSpecial_notes(petDto.getSpecialNotes());
        pet.setOwner(userRepository.findByEmail(petDto.getOwnerEmail()).orElseThrow(() -> new NotFoundException("userNot found")));
        return pet;
    }

    public List<Pet> allToEntity(List<PetDto> dtos) {
        return dtos.stream().map(this::toEntity).toList();
    }
}