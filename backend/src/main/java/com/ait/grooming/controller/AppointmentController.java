package com.ait.grooming.controller;

import com.ait.grooming.dto.appointment.AppointmentDto;
import com.ait.grooming.utils.request.AppointmentRequest;
import com.ait.grooming.service.AppointmentService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
@Data
public class AppointmentController {
    private final AppointmentService appointmentService;
    @GetMapping
    private List<AppointmentDto> getAll(){
        return appointmentService.getAll();
    }

    @PostMapping("/new")
    public AppointmentDto create(@RequestBody AppointmentRequest appointmentRequest) {
        System.out.println(appointmentRequest);
        return appointmentService.create(appointmentRequest);
    }

    @GetMapping("/{id}")
    private AppointmentDto getById(@PathVariable Integer id) {
        return appointmentService.getById(id);
    }
}
