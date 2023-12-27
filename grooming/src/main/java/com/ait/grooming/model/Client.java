package com.ait.grooming.model;

import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
public class Client extends User {
    private boolean isBlocked;
    @ManyToOne
    private Discount discounts;
    @OneToMany
    private List<Pet> pets;
}
