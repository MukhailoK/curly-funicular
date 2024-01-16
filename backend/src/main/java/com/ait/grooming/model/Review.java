//package com.ait.grooming.model;
//
//import lombok.Data;
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "review")
//@Data
//public class Review {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Integer id;
//    @OneToOne
//    @JoinColumn(name = "appointment_id")
//    private Appointment appointment;
//    @Column(name = "rating")
//    private double rating;
//    @Column(name = "review")
//    private String review;
//}