package com.ait.grooming.utils.request;

import lombok.Data;

import java.time.LocalTime;

@Data
public class GroomingRequestDto {
    private Integer id;
    private String name;
    private String size;
    private String description;
    private Double price;
    private LocalTime durationProcedure;
}
