package com.ait.grooming.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
public class Employee extends User {
    @Column(name = "address")
    private String address;
}
