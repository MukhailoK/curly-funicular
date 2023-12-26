package com.ait.grooming.model;


import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Employee extends User {
    private String address;
}
