package com.ait.grooming.dto.pet;

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

