package com.ait.grooming.model;


import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "discounts")
@Data
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private  Client client;
    @Column(name = "discount_rate")
    private double discountRate;
    @Column(name = "total_visits")
    private int totalVisits;
}
