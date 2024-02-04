package com.ait.grooming.controller;

import com.ait.grooming.dto.appointment.AppointmentResponseDto;
import com.ait.grooming.dto.response.ErrorResponse;
import com.ait.grooming.service.AppointmentService;
import com.ait.grooming.utils.request.AppointmentRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
@AllArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping
    public List<AppointmentResponseDto> getAll() {
        return appointmentService.getAll();
    }

    @PostMapping
    public ResponseEntity<AppointmentResponseDto> create(@RequestBody AppointmentRequest appointmentRequest,
                                                         Principal connectedUser) {
        return appointmentService.create(appointmentRequest, connectedUser);
    }

    @GetMapping("/{id}")
    public AppointmentResponseDto getById(@PathVariable Integer id) {
        return appointmentService.getById(id);
    }

    @Operation(description = "get all appointments by token.")
    @GetMapping("/user")
    public ResponseEntity<List<AppointmentResponseDto>> getAllByUserEmail(Principal principal) {
        return appointmentService.getAllByUserEmail(principal.getName());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ErrorResponse> delete(@PathVariable Integer id) {
        return appointmentService.delete(id);
    }


}
