package com.ait.grooming.dto.grooming;

import lombok.Data;

import java.time.LocalTime;

@Data
public class GroomingRequestDto {
    private String name;
    private String size;
    private String description;
    private Double price;
    private LocalTime durationProcedure;
}
