package com.ait.grooming.dto.pet;

import com.ait.grooming.dto.breed.BreedDto;
import com.ait.grooming.dto.client.ClientDto;
import com.ait.grooming.dto.petType.PetTypeDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PetDto {
    private String name;
    private ClientDto owner;
    private PetTypeDto petTypeDto;
    private BreedDto breedDto;
    private String photoUrl;
    private String specialNotes;
}
