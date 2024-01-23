package com.ait.grooming.controller;

import com.ait.grooming.dto.appointment.AppointmentResponseDto;
import com.ait.grooming.service.AppointmentService;
import com.ait.grooming.utils.request.NewUserAppointmentRequest;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    public ResponseEntity<AppointmentResponseDto> create(@RequestBody NewUserAppointmentRequest appointmentRequest,
                                                         Principal connectedUser) {
        return appointmentService.create(appointmentRequest, connectedUser);
    }

    @GetMapping("/{id}")
    private AppointmentResponseDto getById(@PathVariable Integer id) {
        return appointmentService.getById(id);
    }
}
