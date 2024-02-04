package com.ait.grooming.dto.booking;

import lombok.Data;

import java.time.LocalDateTime;

@Data
//for calendar
    public class BookingDto {
        //private Long id;
        private Integer id;
        private LocalDateTime dateTimeStart;
        private String status;
    }

