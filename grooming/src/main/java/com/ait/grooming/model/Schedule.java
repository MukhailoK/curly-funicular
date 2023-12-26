package com.ait.grooming.model;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Data
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "id")
    private Employee employee;
    @Pattern(regexp = "[1-7]")
    private int dayOfWeek;
    LocalTime startTime;
    LocalTime endTime;
}
