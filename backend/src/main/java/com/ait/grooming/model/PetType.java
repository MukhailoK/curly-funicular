package com.ait.grooming.model;

import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
public class PetType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long petTypeId;
    @Column(name = "name")
    private String typeName;
    @OneToMany
    List<Pet> pets;
}
