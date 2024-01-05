package com.ait.grooming.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name",length = 20,nullable = false)
    private String name;
    @Column(name = "lastname",length = 20)
    private String lastName;
    @Column(name = "username",length = 20)
    private String userName;
    @Column(name = "password",length = 20)
    private String password;
    @Column(name = "email",length = 20,nullable = false)
    private String email;
    @Column(name = "phone",length = 20,nullable = false)
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "date_registration")
    private LocalDateTime dateOfRegistration;
  //  private LocalDateTime lastVisitData;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    //Clients
    @Column(name = "is_blocked")
    private boolean isBlocked;
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private List<Discount> discounts;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Pet> pets;

    //employee
    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL)
    List<Schedule> schedules;

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