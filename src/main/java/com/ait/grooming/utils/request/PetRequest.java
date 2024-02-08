package com.ait.grooming.utils.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetRequest {
    private String name;
    //    private String type;
    private Integer breedId;
    //  private String photoUrl;
    private String specialNotes;
}

