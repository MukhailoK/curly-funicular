package com.ait.grooming.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "breeds")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "pets")
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name",length = 20,nullable = false)
    private String name;
    @OneToMany(mappedBy = "breed")
    List<Pet> pets;
}
