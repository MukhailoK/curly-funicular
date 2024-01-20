package com.ait.grooming.controller.booking;

import com.ait.grooming.dto.booking.BookingDto;
import com.ait.grooming.dto.booking.AvailableTimeSlotDto;
import com.ait.grooming.service.booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/available-slots")
    public ResponseEntity<List<AvailableTimeSlotDto>> getAvailableTimeSlots1(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        // Map<LocalDate, Map<Integer, Long>>
        Map<LocalDate, Map<Integer, Integer>> availableSlots = bookingService.analyzeAppointmentsByDay(start, end);

        // Преобразование Map в список DTO
        List<AvailableTimeSlotDto> dtos = availableSlots.entrySet().stream()
                .map(entry -> new AvailableTimeSlotDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
//    @GetMapping
//    public ResponseEntity<List<AvailableTimeSlotDto>> getAvailableTimeSlotSort() {
//        LocalDateTime start = LocalDateTime.now();
//        LocalDateTime end = LocalDateTime.MAX;
//
//        Map<LocalDate, Map<Integer, Integer>> availableSlots = bookingService.analyzeAppointmentsByDay(start, end);
//        // Преобразование Map в список DTO с сортировкой временных слотов
//        List<AvailableTimeSlotDto> dtos = availableSlots.entrySet().stream()
//                .map(entry -> {
//                    return new AvailableTimeSlotDto(entry.getKey(), entry.getValue());
//                })
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(dtos);
//    }
}

