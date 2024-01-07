package com.ait.grooming.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "petTypes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "pets")
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
