package com.ait.grooming.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            insertable = false, updatable = false)
    private Long id;
    @Column(name = "name", length = 20, nullable = false)
    private String name;
    @Column(name = "lastname", length = 20, nullable = false)
    private String lastName;
    @Column(name = "username", length = 20)
    private String userName;
    @Column(name = "password", length = 20)
    private String password;
    @Column(name = "email", length = 20, nullable = false)
    private String email;
    @Column(name = "phone", length = 20, nullable = false)
    private String phone;
    @Column(name = "registration_date")
    private LocalDate registrationDate;
    @ManyToOne
    @JoinColumn(name = "id")
    private Role role;
    @Column(name = "address")
    private String address;
    @OneToMany(mappedBy = "master")
    List<Schedule> schedules;
    @Column(name = "is_blocked")
    private boolean isBlocked;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Discount> discounts;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Pet> pets;
}
