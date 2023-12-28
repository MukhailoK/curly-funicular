package com.ait.grooming.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Data
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private long id;
    @OneToOne
    @JoinColumn(name = "id")
    private Employee employee;
    @Pattern(regexp = "[1-7]")
    @Column(name = "day_of_week")
    private int dayOfWeek;
    @Column(name = "start_time")
    LocalTime startTime;
    @Column(name = "end_time")
    LocalTime endTime;
}
