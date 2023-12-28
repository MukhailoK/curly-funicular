package com.ait.grooming.model;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private long petId;
    @ManyToOne
    @JoinColumn(name = "id")
    private Client owner;
    @ManyToOne
    @JoinColumn(name = "pet_type_id")
    private PetType type;
    @ManyToOne
    @JoinColumn(name = "breed_id")
    private Breed breed;
    @Column(name = "photo_url")
    private String photoUrl;
}
