package com.ait.grooming.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "ratings")
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long reviewId;
    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
    @Column(name = "rating")
    private double rating;
    @Column(name = "review")
    private String review;
}
