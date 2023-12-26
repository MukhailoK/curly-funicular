package com.ait.grooming.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
