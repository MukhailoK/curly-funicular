package com.ait.grooming.model;

import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
public class Client extends User {
    @Column(name = "is_blocked")
    private boolean isBlocked;
    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discounts;
    @OneToMany
    @JoinColumn(name = "pet_id")
    private List<Pet> pets;
}
