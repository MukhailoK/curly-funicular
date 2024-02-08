package com.ait.grooming.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "breeds")
@Data
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 20)
    private String name;
}
