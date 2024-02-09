package com.ait.grooming.dto.booking;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.TreeMap;

@Data
@RequiredArgsConstructor
public class AvailableTimeSlotDto {

    private String date;
    private boolean isFree;
    private TreeMap<Integer, Integer> timeSlots;

    public AvailableTimeSlotDto(LocalDate date, TreeMap<Integer, Integer> timeSlots) {
        this.date = convertLocalDateToString(date);
        this.timeSlots = timeSlots;
        this.isFree = calculateIsFree();
    }

    public static String convertLocalDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }

    private boolean calculateIsFree() {
        return timeSlots.values().stream().anyMatch(Objects::isNull);
    }
}