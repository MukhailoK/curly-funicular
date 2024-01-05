package com.ait.grooming.controller;

import com.ait.grooming.model.Schedule;
import com.ait.grooming.model.User;
import com.ait.grooming.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<User> getAll() {
        return employeeService.getAll();
    }
    @GetMapping("/{id}")
    public List<Schedule> getSchedulesByUserName(@PathVariable Long id){
        return employeeService.getAllScheduleByMasterId(id);
    }
}
