package com.ait.grooming.dto.employee;

import com.ait.grooming.dto.schedule.ScheduleDto;
import com.ait.grooming.model.Schedule;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@RequiredArgsConstructor
public class EmployeeDto {
    private String name;
    private String lastName;
    private String userName;
    private String email;
    private String phone;
    private LocalDate registrationDate;
    private List<ScheduleDto> scheduleDtos;

}
