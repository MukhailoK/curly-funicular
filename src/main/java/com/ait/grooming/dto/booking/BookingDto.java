package com.ait.grooming.dto.booking;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingDto {
    private Integer id;
    private LocalDateTime dateTimeStart;
    private String status;
}

