package com.ait.grooming.service.booking;

import com.ait.grooming.dto.booking.AvailableTimeSlotDto;
import com.ait.grooming.model.Appointment;
import com.ait.grooming.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingService {


    private final BookingRepository bookingRepository;


    public List<AvailableTimeSlotDto> analyzeAppointmentsByDay(LocalDateTime start, LocalDateTime end) {
        if (start.isBefore(LocalDateTime.now().minusMinutes(2))) {
            throw new IllegalArgumentException("wrong value for time start");
        }
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("wrong value for time end");
        }
        List<Appointment> appointments = bookingRepository.findByDateTimeStartBetween(start, end);
        Map<LocalDate, TreeMap<Integer, Integer>> slotResult = new HashMap<>();
        if (appointments != null) {
            for (Appointment appointment : appointments) {
                LocalDate date = appointment.getDateTimeStart().toLocalDate();
                int hour = appointment.getDateTimeStart().getHour();
                slotResult.putIfAbsent(date, new TreeMap<>());
                slotResult.get(date).put(hour, appointment.getId());
            }
            for (LocalDate currentDate : slotResult.keySet()) {
                for (int hour : Arrays.asList(10, 12, 14, 16)) {
                    slotResult.get(currentDate).putIfAbsent(hour, null);
                }
            }
        } else {
            LocalDate startDate = start.toLocalDate();
            TreeMap<Integer, Integer> timeSlots = new TreeMap<>();
            for (int hour : Arrays.asList(10, 12, 14, 16)) {
                timeSlots.put(hour, null);
            }
            slotResult.put(startDate, timeSlots);
        }


        return slotResult
                .entrySet()
                .stream()
                .map(entry -> new AvailableTimeSlotDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

    }

}

