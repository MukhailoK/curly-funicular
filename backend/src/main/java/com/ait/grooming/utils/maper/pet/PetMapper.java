package com.ait.grooming.utils.maper.pet;

import com.ait.grooming.dto.pet.PetDto;
import com.ait.grooming.model.Pet;

import java.util.List;


public class PetMapper {
    public static PetDto toPetDto(Pet pet) {
        PetDto petDto = new PetDto();
        petDto.setName(pet.getName());
        petDto.setOwnerName(pet.getOwner().getName());
//        petDto.setType(pet.getType().name());
        petDto.setBreed(pet.getBreed().getName());
//        petDto.setPhotoUrl(pet.getPhotoUrl());
        petDto.setSpecialNotes(pet.getSpecial_notes());
        return petDto;
    }

    public static List<PetDto> allToPetDto(List<Pet> pets) {
        return pets.stream().map(PetMapper::toPetDto).toList();
    }
}