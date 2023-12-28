package com.ait.grooming.model;


import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private long discountId;
    @OneToMany
    @JoinColumn(name = "id")
    private List<Client> clientId;
    @Column(name = "discount_rate")
    private double discountRate;
    @Column(name = "total_visit")
    private int totalVisits;
}
