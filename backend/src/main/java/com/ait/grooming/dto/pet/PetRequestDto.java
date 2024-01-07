package com.ait.grooming.dto.pet;

import com.ait.grooming.model.Breed;
import com.ait.grooming.model.PetType;
import com.ait.grooming.model.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class PetRequestDto {
    private String name;
    private User owner;
    private PetType type;
    private Breed breed;
    private String photoUrl;
    private String specialNotes;
}

