//package com.ait.grooming.service;
//
//import com.ait.grooming.model.Role;
//import com.ait.grooming.model.User;
//import com.ait.grooming.repository.EmployeeRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class EmployeeService {
//
//    private final EmployeeRepository employeeRepository;
//
//    public List<User> getAll() {
//        return employeeRepository.findAll()
//                .stream()
//                .filter(user -> user.getRole().equals(Role.MASTER))
//                .collect(Collectors.toList());
//    }
//
//}
