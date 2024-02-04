package com.ait.grooming.model;

import lombok.Data;

import jakarta.persistence.*;
import lombok.ToString;

@Entity
@Table(name = "pets")
@Data
@ToString(exclude = "owner")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name",length = 20,nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @ManyToOne
    @JoinColumn(name = "breed_id")
    private Breed breed;
    @Column(name = "special_notes")
    private String special_notes;
}
