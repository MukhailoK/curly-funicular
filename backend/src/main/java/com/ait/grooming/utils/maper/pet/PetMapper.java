package com.ait.grooming.utils.maper.pet;

import com.ait.grooming.dto.pet.PetDto;
import com.ait.grooming.model.Pet;

import java.util.List;

import static com.ait.grooming.utils.maper.breed.BreedMapper.toBreedDto;
import static com.ait.grooming.utils.maper.client.ClientMapper.toClientDto;


public class PetMapper {
    public static PetDto toPetDto(Pet pet) {
        PetDto petDto = new PetDto();
        petDto.setName(pet.getName());
     //   petDto.setOwner(toClientDto(pet.getOwner()));
        //    petDto.setPetTypeDto(Pet.PetType.DOG);
       // petDto.setBreedDto(toBreedDto(pet.getBreed()));
        petDto.setPhotoUrl(pet.getPhotoUrl());
        petDto.setSpecialNotes(pet.getSpecial_notes());
        return petDto;
    }

    public static List<PetDto> allToPetDto(List<Pet> pets) {
        return pets.stream().map(PetMapper::toPetDto).toList();
    }
}