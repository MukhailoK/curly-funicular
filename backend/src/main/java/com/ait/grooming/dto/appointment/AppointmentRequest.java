package com.ait.grooming.dto.appointment;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class AppointmentRequest {
    private String clientEmail;
    private String masterUserName;
    private Long groomingId;
    private Long petId;
    private LocalDateTime dateTimeStart;
    private LocalDateTime dateTimeEnd;
    private String status;
}
