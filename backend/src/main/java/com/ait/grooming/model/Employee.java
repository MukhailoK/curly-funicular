package com.ait.grooming.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Employee extends User {
    @Column(name = "address")
    private String address;
    @OneToOne
    @JoinColumn(name = "schadule_id")
    Schedule schedule;
}
