package com.ait.grooming.service.booking;

import com.ait.grooming.dto.booking.AvailableTimeSlotDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ait.grooming.dto.booking.BookingDto;
import com.ait.grooming.model.*;
import com.ait.grooming.repository.BookingRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Метод для анализа Appointments и формирования коллекции занятых временных слотов

    public List<AvailableTimeSlotDto> analyzeAppointmentsByDay(LocalDateTime start, LocalDateTime end) {
        //   try {
        if (start.isBefore(LocalDateTime.now().minusMinutes(2))) {
            throw new IllegalArgumentException("Неверное значение времени начала");
        }
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("Неверное значение времени окончания");
        }
        // Получение всех визитов в заданном временном интервале
        // List<Appointment> appointments = bookingRepository.findAll();
        List<Appointment> appointments = bookingRepository.findByDateTimeStartBetween(start, end);
        Map<LocalDate, TreeMap<Integer, Integer>> slotResult = new HashMap<>();
        if (appointments != null) {
            // Создание коллекции для хранения данных slot
            // Проход по всем визитам и анализ времени
            for (Appointment appointment : appointments) {
                LocalDate date = appointment.getDateTimeStart().toLocalDate();
                int hour = appointment.getDateTimeStart().getHour();

                // Инициализация внутренней мапы для даты, если ее еще нет
                slotResult.putIfAbsent(date, new TreeMap<>());
                //TODO анализ на статус для следующего релиза
                slotResult.get(date).put(hour, appointment.getId());
            }
            // Добавление пропущенных значений (null) в мапу для каждого времени
            for (LocalDate currentDate : slotResult.keySet()) {
                for (int hour : Arrays.asList(10, 12, 14, 16)) {
                    slotResult.get(currentDate).putIfAbsent(hour, null);
                }
            }
        } else {
            //return null;
            LocalDate startDate = start.toLocalDate();
            TreeMap<Integer, Integer> timeSlots = new TreeMap<>();
            for (int hour : Arrays.asList(10, 12, 14, 16)) {
                timeSlots.put(hour, null);
            }
            slotResult.put(startDate, timeSlots);
        }


        List<AvailableTimeSlotDto> availableSlots = slotResult.entrySet().stream()
                .map(entry -> {
                    return new AvailableTimeSlotDto(entry.getKey(), entry.getValue());
                })
                .collect(Collectors.toList());

        return availableSlots;

    }

}

