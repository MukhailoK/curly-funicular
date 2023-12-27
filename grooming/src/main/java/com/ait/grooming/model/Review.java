package com.ait.grooming.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ratingId;
    @OneToOne
    private Appointment appointment;
    private double rating;
    private String review;
}
