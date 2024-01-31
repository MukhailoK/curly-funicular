package com.ait.grooming.controller;

import com.ait.grooming.dto.appointment.AppointmentResponseDto;
import com.ait.grooming.service.AppointmentService;
import com.ait.grooming.utils.request.NewUserAppointmentRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/openapi/v1/appointments")
@AllArgsConstructor
public class AppointmentOpenController {

    private final AppointmentService appointmentService;


    @PostMapping
    public ResponseEntity<AppointmentResponseDto> create(@RequestBody NewUserAppointmentRequest appointmentRequest) {
        return appointmentService.create(appointmentRequest);
    }

    @Operation(description = "get all appointments by user email. Is correct send email in path variable for this?")
    @GetMapping("/users/{email}")
    public ResponseEntity<List<AppointmentResponseDto>> getAllByUserEmail(@PathVariable String email) {
        return appointmentService.getAllByUserEmail(email);
    }

}
