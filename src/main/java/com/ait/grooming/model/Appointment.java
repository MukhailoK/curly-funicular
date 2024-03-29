package com.ait.grooming.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Data
@RequiredArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private User client;
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Grooming groomingService;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pet_id")
    private Pet pet;
    @Column(name = "date_time_start")
    private LocalDateTime dateTimeStart;
    @Column(name = "date_time_end")
    private LocalDateTime dateTimeEnd;
    @Column(name = "status")
    private String status;
}
