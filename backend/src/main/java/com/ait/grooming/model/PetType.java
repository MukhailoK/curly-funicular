package com.ait.grooming.model;

import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "petTypes")
@Data
public class PetType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name",length = 10,nullable = false)
    private String name;
    @OneToMany(mappedBy = "type")
    List<Pet> pets;
}
