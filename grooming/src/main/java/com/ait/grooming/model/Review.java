package com.ait.grooming.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private long reviewId;
    @OneToOne
    private Appointment appointment;
    @Column(name = "rating")
    private double rating;
    @Column(name = "review")
    private String review;
}
