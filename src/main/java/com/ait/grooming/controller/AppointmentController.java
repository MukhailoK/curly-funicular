package com.ait.grooming.controller;

import com.ait.grooming.dto.appointment.AppointmentResponseDto;
import com.ait.grooming.dto.response.Response;
import com.ait.grooming.service.AppointmentService;
import com.ait.grooming.utils.request.AppointmentRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Operation(description = "get all appointments")
    public List<AppointmentResponseDto> getAll() {
        return appointmentService.getAll();
    }

    @PostMapping
    @Operation(description = "create appointment for connected user")
    public ResponseEntity<AppointmentResponseDto> create(@RequestBody AppointmentRequest appointmentRequest,
                                                         Principal connectedUser) {
        return appointmentService.create(appointmentRequest, connectedUser);
    }

    @Operation(description = "get appointment by id")
    @GetMapping("/{id}")
    @Schema(example = "1")
    public AppointmentResponseDto getById(@PathVariable Integer id) {
        return appointmentService.getById(id);
    }

    @Operation(description = "get all user's appointments by connected user")
    @GetMapping("/user")
    public ResponseEntity<List<AppointmentResponseDto>> getAllByUserEmail(Principal principal) {
        return appointmentService.getAllByUserEmail(principal.getName());
    }

    @DeleteMapping("/{id}")
    @Operation(description = "delete appointment by id")
    public ResponseEntity<Response> delete(@PathVariable Integer id) {
        return appointmentService.delete(id);
    }


}
