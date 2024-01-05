package com.ait.grooming.model;

import lombok.Data;

import jakarta.persistence.*;
  //ADMIN,CLIENT,MASTER,GUEST

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name",length = 20,nullable = false)
    private String name;
}

