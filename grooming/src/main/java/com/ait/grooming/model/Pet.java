package com.ait.grooming.model;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long petId;
    @ManyToOne
    @JoinColumn(name = "id")
    private Client owner;
    @ManyToOne
    @JoinColumn(name = "petTypeId")
    private PetType type;
    @ManyToOne
    @JoinColumn(name = "breedId")
    private Breed breed;
    private String photoUrl;
}
