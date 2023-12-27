package com.ait.grooming.model;


import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
public class Employee extends User {
    private String address;
}
