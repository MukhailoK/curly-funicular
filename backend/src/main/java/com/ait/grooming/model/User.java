package com.ait.grooming.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@ToString(exclude = {"pets","discounts","schedules"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            insertable = false, updatable = false)
    private Long id;
    @Column(name = "name", length = 20, nullable = false)
    private String name;
    @Column(name = "lastname", length = 20)
    private String lastName;
    @Column(name = "username", length = 20)
    private String userName;
    @Column(name = "password", length = 20)
    private String password;
    @Column(name = "email", length = 20, nullable = false)
    private String email;
    @Column(name = "phone", length = 20, nullable = false)
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "date_registration")
    private LocalDate registrationDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToMany(mappedBy = "master",cascade = CascadeType.ALL)
    List<Schedule> schedules;
    @Column(name = "is_blocked")
    private boolean isBlocked;
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private List<Discount> discounts;
    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL)
    private List<Pet> pets;
}