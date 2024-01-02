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
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "master_id")
    private Employee master;
    @ManyToOne
    @JoinColumn(name = "service_id")
    private GroomingService groomingService;
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
    @Column(name = "date_time_start")
    private LocalDateTime dateTimeStart;
    @Column(name = "date_time_end")
    private LocalDateTime dateTimeEnd;
    @Column(name = "status")
    private String status;
}
