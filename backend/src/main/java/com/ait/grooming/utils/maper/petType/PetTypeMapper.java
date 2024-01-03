package com.ait.grooming.utils.maper.petType;

import com.ait.grooming.dto.petType.PetTypeDto;
import com.ait.grooming.model.PetType;

import java.util.List;

public class PetTypeMapper {
    public static PetTypeDto toPetTypeDto(PetType petType) {
        PetTypeDto petTypeDto = new PetTypeDto();
        petTypeDto.setName(petType.getName());
        return petTypeDto;
    }

    public static List<PetTypeDto> allToPetTypeDto(List<PetType> petTypes) {
        return petTypes.stream().map(PetTypeMapper::toPetTypeDto).toList();
    }
}
