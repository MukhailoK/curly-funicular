package com.ait.grooming.dto.review;

import com.ait.grooming.dto.appointment.AppointmentDto;
import com.ait.grooming.model.Appointment;
import lombok.Data;

@Data
public class ReviewDto {
    private Long id;
    private AppointmentDto appointment;
    private Double rating;
    private String review;
}
