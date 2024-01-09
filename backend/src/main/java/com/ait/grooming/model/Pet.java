package com.ait.grooming.model;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Table(name = "pets")
@Data
public class Pet {
    public enum  PetType {
       DOG, CAT
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name",length = 20,nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PetType type;
    @ManyToOne
    @JoinColumn(name = "breed_id")
    private Breed breed;
    @Column(name = "photo_url")
    private String photoUrl;
    @Column(name = "special_notes")
    private String special_notes;
}
