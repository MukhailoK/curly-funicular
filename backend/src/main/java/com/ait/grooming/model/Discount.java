package com.ait.grooming.model;


import lombok.Data;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "discounts")
@Data
@ToString(exclude = "client")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name",length = 50,nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private  User client;
    @Column(name = "discount_rate")
    private double discountRate;
    @Column(name = "total_visits")
    private int totalVisits;
}
