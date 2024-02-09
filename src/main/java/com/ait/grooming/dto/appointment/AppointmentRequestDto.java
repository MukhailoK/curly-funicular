package com.ait.grooming.dto.appointment;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class AppointmentRequestDto {
    private String name;
    private String lastName;
    private String userName;
    private String email;
    private String phone;
    private String nameDog;
    private String breed;
    private String specialNotes;
    private Integer idGrooming;
    private LocalDateTime dateTimeStart;

}
