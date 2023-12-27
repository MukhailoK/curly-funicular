package com.ait.grooming.model;


import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long discountId;
    @OneToMany
    @JoinColumn(name = "id")
    private List<Client> clientId;
    private double discountRate;
    private int totalVisits;
}
