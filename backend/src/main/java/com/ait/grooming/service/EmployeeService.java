package com.ait.grooming.service;

import com.ait.grooming.model.Schedule;
import com.ait.grooming.model.User;
import com.ait.grooming.repository.EmployeeRepository;
import com.ait.grooming.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ScheduleRepository scheduleRepository;

    public List<User> getAll() {
        return employeeRepository.findAll()
                .stream()
                .filter(user -> user.getRole().getName().equalsIgnoreCase("master"))
                .collect(Collectors.toList());
    }

    public List<Schedule> getAllScheduleByMasterId(Long id) {
        return scheduleRepository.getAllByMasterId(id);
    }
}
