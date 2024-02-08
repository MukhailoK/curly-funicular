package com.ait.grooming.dto.appointment;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class AppointmentRequestDto {
    //User новый
    private String name;
    private String lastName;
    private String userName;
    private String email;
    private String phone;
    //добавить собаку
    private String nameDog;
    private String breed;
    private String specialNotes;
    //добавить услугу
    private Integer idGrooming;
    //добавить время процедуры
    private LocalDateTime dateTimeStart;

}
