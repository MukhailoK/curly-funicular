package com.ait.grooming.service;

import com.ait.grooming.dto.appointment.AppointmentDto;
import com.ait.grooming.dto.appointment.AppointmentRequest;
import com.ait.grooming.model.*;
import com.ait.grooming.repository.*;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ait.grooming.utils.maper.appointment.AppointmentMapper.allToAppointmentDto;
import static com.ait.grooming.utils.maper.appointment.AppointmentMapper.toAppointmentDto;

@Service
@Data
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final GroomingRepository groomingRepository;
    private final PetRepository petRepository;

    public AppointmentDto create(AppointmentRequest appointmentRequest) {
        Client client = clientRepository.findByEmail(appointmentRequest.getClientEmail())
                .orElseThrow(() -> new IllegalArgumentException("client not found"));
        Employee employee = employeeRepository.findByUserName(appointmentRequest.getMasterUserName())
                .orElseThrow(() -> new IllegalArgumentException("master not found"));
        Grooming grooming = groomingRepository.findById(appointmentRequest.getGroomingId())
                .orElseThrow(() -> new IllegalArgumentException("grooming service not found"));
        Pet pet = petRepository.findById(appointmentRequest.getPetId())
                .orElseThrow(() -> new IllegalArgumentException("pet not found"));
        Appointment appointment = new Appointment();
        appointment.setClient(client);
        appointment.setMaster(employee);
        appointment.setGroomingService(grooming);
        appointment.setPet(pet);
        appointment.setDateTimeStart(appointmentRequest.getDateTimeStart());
        appointment.setDateTimeEnd(appointmentRequest.getDateTimeEnd());
        appointment.setStatus(appointmentRequest.getStatus());
        appointmentRepository.save(appointment);
        return toAppointmentDto(appointment);
    }

    public AppointmentDto getById(Long id) {
        return toAppointmentDto(appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("appointment not found")));
    }

    public List<AppointmentDto> getAll() {
        return allToAppointmentDto(appointmentRepository.findAll());
    }
}
