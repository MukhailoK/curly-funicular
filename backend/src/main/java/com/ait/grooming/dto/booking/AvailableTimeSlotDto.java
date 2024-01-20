package com.ait.grooming.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
//@AllArgsConstructor
@RequiredArgsConstructor
    public class AvailableTimeSlotDto {
    // уже запланированная дата визита
    private LocalDate date;
    //если есть хоть один слот
    private boolean isFree;
    //ключ время (10, 12, 14, 16)
    // значение - идентификатор визита (Appointment_ID) или null, если слот свободен
   // private Map<Integer, Long> timeSlots;
    private Map<Integer, Integer> timeSlots;

    public AvailableTimeSlotDto(LocalDate date, Map<Integer, Integer> timeSlots) {
        this.date = date;
        this.timeSlots = timeSlots;//sortTimeSlots(timeSlots);
        this.isFree = calculateIsFree();
    }

    private Map<Integer, Long> sortTimeSlots(Map<Integer, Long> unsortedTimeSlots) {
       return unsortedTimeSlots.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private boolean calculateIsFree() {
            return timeSlots.values().stream().anyMatch(value -> value == null);
        }
    }