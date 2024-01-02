package com.ait.grooming.model;

import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "grooming_services")
@Data
public class GroomingService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long serviceId;
    @Column(name = "name",length = 20,nullable = false)
    private String name;
    @Column(name = "size",length = 50,nullable = false)
    private String size;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "duration_procedure")
    private LocalTime durationProcedure;
    @Column(name = "active")
    private Boolean active = false;
}
