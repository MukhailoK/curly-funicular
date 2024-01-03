package com.ait.grooming.model;

import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients")
@Data
public class Client extends User {
    @Column(name = "is_blocked")
    private boolean isBlocked;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Discount> discounts;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Pet> pets;
}
