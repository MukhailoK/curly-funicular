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
    @Column(name = "appointment_id")
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
    @JoinColumn(name = "pet_id")
    private List<Pet> pets;
    @Column(name = "date_time_start")
    private LocalDateTime dateTimeStart;
    @Column(name = "date_time_end")
    private LocalDateTime dateTimeEnd;
    @Column(name = "status")
    private String status;
}
