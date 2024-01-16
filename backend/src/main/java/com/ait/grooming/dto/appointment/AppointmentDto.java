package com.ait.grooming.dto.appointment;

import com.ait.grooming.dto.client.ClientDto;
import com.ait.grooming.dto.employee.EmployeeDto;
import com.ait.grooming.dto.grooming.GroomingDto;
import com.ait.grooming.dto.pet.PetDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class AppointmentDto {
    private ClientDto clientDto;
  //  private EmployeeDto employeeDto;
    private GroomingDto groomingDto;
    private PetDto petDto;
    private LocalDateTime dateTimeStart;
    private LocalDateTime dateTimeEnd;
    private String status;
}
