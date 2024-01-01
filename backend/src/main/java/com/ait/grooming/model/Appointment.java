package com.ait.grooming.model;

import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "appointments")
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @OneToOne
    @JoinColumn(name = "master_id")
    private Employee master;
    @OneToOne
    @JoinColumn(name = "servise_id")
    private GroomingService groomingService;
    @OneToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
    @Column(name = "date_time_start")
    private LocalDateTime dateTimeStart;
    @Column(name = "date_time_end")
    private LocalDateTime dateTimeEnd;
    @Column(name = "status")
    private String status;
}
