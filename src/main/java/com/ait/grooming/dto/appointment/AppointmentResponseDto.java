package com.ait.grooming.dto.appointment;

import com.ait.grooming.dto.grooming.GroomingDto;
import com.ait.grooming.dto.pet.PetDto;
import com.ait.grooming.dto.user.UserDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class AppointmentResponseDto {
    private LocalDateTime dateTimeStart;
    private GroomingDto groomingDto;
    private UserDto userDto;
    private PetDto petDto;
    private String status;
}
