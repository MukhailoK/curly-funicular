package com.ait.grooming.utils.maper.employee;

import com.ait.grooming.dto.employee.EmployeeDto;
import com.ait.grooming.model.User;

import java.util.List;

import static com.ait.grooming.utils.maper.schedule.ScheduleMapper.allToScheduleDto;

public class EmployeeMapper {
    public static EmployeeDto toEmployeeDto(User employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName(employee.getName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPhone(employee.getPhone());
        employeeDto.setRegistrationDate(employee.getRegistrationDate());
        employeeDto.setScheduleDtos(allToScheduleDto(employee.getSchedules()));
        return employeeDto;
    }

    private static List<EmployeeDto> allToEmployeeDtos(List<User> employees) {
        return employees.stream().map(EmployeeMapper::toEmployeeDto).toList();
    }
}
