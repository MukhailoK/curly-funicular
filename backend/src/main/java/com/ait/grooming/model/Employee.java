package com.ait.grooming.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "employees")
@Entity
public class Employee extends User {
    @Column(name = "address")
    private String address;
    @OneToMany(mappedBy = "master")
    List<Schedule> schedules;
}
