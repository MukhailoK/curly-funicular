package com.ait.grooming.controller.booking;

import com.ait.grooming.dto.booking.AvailableTimeSlotDto;
import com.ait.grooming.service.booking.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;


    @GetMapping("/available-slots")
    public ResponseEntity<List<AvailableTimeSlotDto>> getAvailableTimeSlots1(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

        List<AvailableTimeSlotDto> dtos = bookingService.analyzeAppointmentsByDay(start, end);

        return ResponseEntity.ok(dtos);
    }

    @GetMapping
    public ResponseEntity<List<AvailableTimeSlotDto>> getAvailableTimeSlotSort() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusYears(10); //LocalDateTime.MAX.minusYears(100);

        List<AvailableTimeSlotDto> dtos = bookingService.analyzeAppointmentsByDay(start, end);
        return ResponseEntity.ok(dtos);
    }
}

