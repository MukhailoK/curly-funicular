package com.ait.grooming.model;

import lombok.Data;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
//ADMIN,CLIENT,MASTER,GUEST

@Entity
@Table(name = "roles")
@Data
@RequiredArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name",length = 20,nullable = false)
    private String name;
//    @OneToMany(mappedBy = "role")
//    private List<User> users;

    public Role(int i, String client) {
    }
}

