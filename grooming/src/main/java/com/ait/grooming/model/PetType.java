package com.ait.grooming.model;

import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
public class PetType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long petTypeId;
    private String typeName;
    @OneToMany
    List<Pet> pets;
}
