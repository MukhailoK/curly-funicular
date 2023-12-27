package com.ait.grooming.model;

import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany
    @JoinColumn(name = "id")
    private List<Client> clients;
    @OneToMany
    @JoinColumn(name = "id")
    private List<Employee> employees;
    @ManyToOne
    @JoinColumn(name = "serviseId")
    GroomingService groomingService;
    @OneToMany
    @JoinColumn(name = "petId")
    private List<Pet> pets;
    private LocalDateTime dateTimeStart;
    private LocalDateTime dateTimeEnd;
    private String status;
}
