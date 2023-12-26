package com.ait.grooming.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long breedId;
    private String name;
    @OneToMany
    List<Pet> pets;
}
