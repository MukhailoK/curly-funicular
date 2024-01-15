package com.ait.grooming.dto.pet;

import com.ait.grooming.dto.breed.BreedDto;
import com.ait.grooming.model.Breed;
import com.ait.grooming.model.Pet;
import com.ait.grooming.model.User;
import lombok.Data;

@Data
public class PetDto {
    private String name;
    private String ownerName;
    private String type;
    private String breed;
    private String photoUrl;
    private String specialNotes;
}

