package com.ait.grooming.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Data
//@AllArgsConstructor
@RequiredArgsConstructor
    public class AvailableTimeSlotDto {
    // уже запланированная дата визита
   // private LocalDate date;
    private String date;
    //если есть хоть один слот
    private boolean isFree;
    //ключ время (10, 12, 14, 16)
    // значение - идентификатор визита (Appointment_ID) или null, если слот свободен
   // private TreeMap<Integer, Long> timeSlots;
    private TreeMap<Integer, Integer> timeSlots;

    public AvailableTimeSlotDto(LocalDate date, TreeMap<Integer, Integer> timeSlots) {
        this.date = convertLocalDateToString(date);
        this.timeSlots = timeSlots;
        this.isFree = calculateIsFree();
    }
    public static String convertLocalDateToString(LocalDate date) {
        // Указываем желаемый формат даты
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Преобразовываем LocalDate в строку
        String formattedDate = date.format(formatter);

        return formattedDate;
    }

    private boolean calculateIsFree() {
            return timeSlots.values().stream().anyMatch(value -> value == null);
        }
    }