package com.ait.grooming.controller;

import com.ait.grooming.dto.appointment.AppointmentResponseDto;
import com.ait.grooming.utils.request.AppointmentRequest;
import com.ait.grooming.service.AppointmentService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@Data
public class AppointmentController {
    private final AppointmentService appointmentService;
    @GetMapping
    private List<AppointmentResponseDto> getAll(){
        return appointmentService.getAll();
    }

    @PostMapping
    public ResponseEntity<AppointmentResponseDto> create(@RequestBody AppointmentRequest appointmentRequest) {
        System.out.println(appointmentRequest);
        return appointmentService.create(appointmentRequest);
    }

    @GetMapping("/{id}")
    private AppointmentResponseDto getById(@PathVariable Integer id) {
        return appointmentService.getById(id);
    }
}
