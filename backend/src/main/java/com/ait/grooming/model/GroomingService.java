package com.ait.grooming.model;

import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Data
public class GroomingService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private long serviceId;
    @Column(name = "name")
    private String name;
    @Column(name = "size")
    private String size;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "duration_procedure")
    private LocalTime durationProcedure;
}
