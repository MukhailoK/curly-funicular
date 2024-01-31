package com.ait.grooming.service.booking;

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

        // Метод для получения всех визитов в определенном временном интервале
        public List<BookingDto> getAppointmentsByTimeInterval(LocalDateTime start, LocalDateTime end) {
           if (start.isBefore(LocalDateTime.now())) //LocalDateTime.now()+1
           {
              LocalDateTime.now();
               // Выбрасываем исключение так как время планируемого визита не может быть раньше текущего
             //  throw new IllegalArgumentException("Неверное значение времени начала");
           }
          List<Appointment> appointments = bookingRepository.findByDateTimeStartBetween(start, end);
         // List<Appointment> appointments = appointmentRepository.findAll();
            // Преобразование сущности в DTO
            List<BookingDto> appointmentDtos = appointments.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());

            return appointmentDtos;
        }

        // метод для преобразования сущности в DTO
        private BookingDto convertToDto(Appointment appointment) {
            BookingDto dto = new BookingDto();
            dto.setId(appointment.getId());
            dto.setDateTimeStart(appointment.getDateTimeStart());
            dto.setStatus(appointment.getStatus());
             System.out.println(dto.getId());
            return dto;
        }

    // Метод для анализа Appointments  и формирования коллекции веменных слотов
    //Todo сделать полноценный метод для следующего релиза по новый фронт
    public Map<LocalDate, TreeMap<Integer, Integer>> analyzeAppointmentsByDay(LocalDateTime start, LocalDateTime end) {

        if (start.isBefore(LocalDateTime.now())) //LocalDateTime.now()+1
        {
            start=LocalDateTime.now();
            // Выбрасываем исключение так как время планируемого визита не может быть раньше текущего
            throw new IllegalArgumentException("Неверное значение времени начала");
        }

        // Получение всех визитов в заданном временном интервале

      //  List<Appointment> appointments = bookingRepository.findByDateTimeStartBetween(start, end);
        List<Appointment> appointments = bookingRepository.findAll();
        // Создание коллекции для хранения данных
            Map<LocalDate, TreeMap<Integer, Integer>> result = new HashMap<>();
        // Проход по всем визитам и анализ времени
        for (Appointment appointment : appointments) {
            LocalDate date = appointment.getDateTimeStart().toLocalDate();
            int hour = appointment.getDateTimeStart().getHour();

            // Инициализация внутренней мапы для даты, если ее еще нет
            result.putIfAbsent(date, new TreeMap<>());
            //TODO анализ на статус для следующего релиза
            // Добавление данных в мапу
            result.get(date).put(hour, appointment.getId());
        }
        // Добавление пропущенных значений (null) в мапу для каждого времени
        for (LocalDate currentDate : result.keySet()) {
            for (int hour : Arrays.asList(10, 12, 14, 16)) {
                result.get(currentDate).putIfAbsent(hour, null);
            }
        }
        return result;
    }
}

