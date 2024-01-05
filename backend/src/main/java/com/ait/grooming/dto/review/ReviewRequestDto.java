package com.ait.grooming.dto.review;

import com.ait.grooming.model.Appointment;
import lombok.Data;

@Data
public class ReviewRequestDto {
    private Long appointmentId;
    private Double rating;
    private String review;
}
