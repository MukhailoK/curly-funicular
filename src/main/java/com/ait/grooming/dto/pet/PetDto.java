package com.ait.grooming.dto.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDto {
    private String name;
    private String ownerEmail;
    private String breed;
    private String specialNotes;
}