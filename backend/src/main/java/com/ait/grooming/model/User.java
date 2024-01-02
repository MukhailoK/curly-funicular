package com.ait.grooming.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@MappedSuperclass
@Getter
@Setter
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name",length = 20,nullable = false)
    private String name;
    @Column(name = "lastname",length = 20,nullable = false)
    private String lastName;
    @Column(name = "username",length = 20)
    private String userName;
    @Column(name = "password",length = 20)
    private String password;
    @Column(name = "email",length = 20,nullable = false)
    private String email;
    @Column(name = "phone",length = 20,nullable = false)
    private String phone;
    @Column(name = "date_registration")
    private LocalDate dateOfRegistration;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
//    @Enumerated(EnumType.STRING)
//    @Column(name = "role")
//    private UserRole role;
}

//public enum UserRole {
//    ADMIN,
//    CLIENT,
//    MASTER,
//    GUEST
//}