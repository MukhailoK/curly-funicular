package com.ait.grooming.dto.review;

import com.ait.grooming.dto.appointment.AppointmentDto;
import com.ait.grooming.model.Appointment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewDto {
    private Integer id;
    private String nameClient;
    private Double rating;
    private String review;
}
