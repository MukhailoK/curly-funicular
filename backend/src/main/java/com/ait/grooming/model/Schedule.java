package com.ait.grooming.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Table(name = "schedules")
@Data
public class
Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "master_id")
    private User master;
    @Pattern(regexp = "[1-7]")
    @Column(name = "day_of_week")
    private int dayOfWeek;
    @Column(name = "start_time")
    private LocalTime startTime;
    @Column(name = "end_time")
    private LocalTime endTime;
}
