package com.ait.grooming.dto.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetRequest {
    private String name;
    private String breed;
    private String specialNotes;

    public static PetDto petDto(PetRequest request) {
        PetDto dto = new PetDto();
        dto.setBreed(request.getBreed());
        dto.setSpecialNotes(request.specialNotes);
        dto.setName(request.name);
        return dto;
    }

    public static List<PetDto> allToPetDto(List<PetRequest> requests) {
        return requests.stream().map(PetRequest::petDto).toList();
    }
}
