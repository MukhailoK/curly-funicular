package com.ait.grooming.dto.review;

import com.ait.grooming.model.Appointment;
import lombok.Data;

@Data
public class ReviewDto {
    private Integer id;
    private Appointment appointment;
    private Double rating;
    private String review;
}
