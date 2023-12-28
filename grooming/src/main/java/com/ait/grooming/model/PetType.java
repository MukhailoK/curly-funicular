package com.ait.grooming.model;

import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
public class PetType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_type_id")
    private long petTypeId;
    @Column(name = "type_name")
    private String typeName;
    @OneToMany
    List<Pet> pets;
}
