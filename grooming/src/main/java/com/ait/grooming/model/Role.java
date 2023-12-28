package com.ait.grooming.model;

import lombok.Data;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long id;
    @Column(name = "name")
    private String name;
}
