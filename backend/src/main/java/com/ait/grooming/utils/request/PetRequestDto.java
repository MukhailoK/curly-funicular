package com.ait.grooming.utils.request;

import com.ait.grooming.model.Breed;

import com.ait.grooming.model.Pet;
import lombok.Data;

@Data
public class PetRequestDto {
    private String name;
    private String ownerEmail;
    private Pet.PetType type;
    private Breed breed;
  //  private String photoUrl;
    private String specialNotes;
}

