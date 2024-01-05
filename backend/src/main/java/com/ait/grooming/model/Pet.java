package com.ait.grooming.model;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Table(name = "pets")
@Data
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name",length = 20,nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @ManyToOne
    @JoinColumn(name = "pet_type_id")
    private PetType type;
    @ManyToOne
    @JoinColumn(name = "breed_id")
    private Breed breed;
    @Column(name = "photo_url")
    private String photoUrl;
    @Column(name = "special_notes")
    private String special_notes;
}
