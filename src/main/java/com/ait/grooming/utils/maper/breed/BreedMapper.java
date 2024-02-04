package com.ait.grooming.utils.maper.breed;

import com.ait.grooming.dto.breed.BreedDto;
import com.ait.grooming.model.Breed;

import java.util.List;

public class BreedMapper {

    public static BreedDto toBreedDto(Breed breed) {
        BreedDto breedDto = new BreedDto();
        breedDto.setName(breed.getName());
        return breedDto;
    }

    public static List<BreedDto> allToBreedDto(List<Breed> breeds) {
        return breeds.stream().map(BreedMapper::toBreedDto).toList();
    }
}
