package com.ait.grooming.utils.request;

import com.ait.grooming.model.Pet;
import lombok.Data;

@Data
public class PetRequest {
    private String name;
//    private String type;
    private Integer breedId;
    //  private String photoUrl;
    private String specialNotes;
}

