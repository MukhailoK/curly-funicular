package com.ait.grooming.controller;

import com.ait.grooming.dto.appointment.AppointmentResponseDto;
import com.ait.grooming.service.AppointmentService;
import com.ait.grooming.utils.request.NewUserAppointmentRequest;
import io.swagger.v3.oas.annotations.Operation;
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
    public List<AppointmentResponseDto> getAll(){
        return appointmentService.getAll();
    }

    @PostMapping
    public ResponseEntity<AppointmentResponseDto> create(@RequestBody NewUserAppointmentRequest appointmentRequest,
                                                         Principal connectedUser) {
        return appointmentService.create(appointmentRequest, connectedUser);
    }

    @GetMapping("/{id}")
    public AppointmentResponseDto getById(@PathVariable Integer id) {
        return appointmentService.getById(id);
    }

    @Operation(description = "get all appointments by user email. Is correct send email in path variable for this?")
    @GetMapping("/users/{email}")
    public ResponseEntity<List<AppointmentResponseDto>> getAllByUserEmail(@PathVariable String email){
        return appointmentService.getAllByUserEmail(email);
    }

}
