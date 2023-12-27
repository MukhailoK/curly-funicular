package com.ait.grooming.model;

import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Data
public class GroomingService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long serviceId;
    private String name;
    private String description;
    private double price;
    private LocalTime durationProcedure;
}
