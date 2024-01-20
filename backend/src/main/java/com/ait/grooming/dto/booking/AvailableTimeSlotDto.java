package com.ait.grooming.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.*;
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
   // private TreeMap<Integer, Long> timeSlots;
    private TreeMap<Integer, Integer> timeSlots;

    public AvailableTimeSlotDto(LocalDate date, TreeMap<Integer, Integer> timeSlots) {
        this.date = date;
        this.timeSlots = timeSlots;
        this.isFree = calculateIsFree();
    }

    private boolean calculateIsFree() {
            return timeSlots.values().stream().anyMatch(value -> value == null);
        }
    }