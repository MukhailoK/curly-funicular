package com.ait.grooming.dto.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDto {
    private String name;
    private String ownerEmail;
//    private String type;
    private String breed;
//    private String photoUrl;
    private String specialNotes;
}