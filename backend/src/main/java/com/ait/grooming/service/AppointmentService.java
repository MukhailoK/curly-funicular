package com.ait.grooming.service;

import com.ait.grooming.dto.appointment.AppointmentResponseDto;
import com.ait.grooming.utils.request.AppointmentRequest;
import com.ait.grooming.model.*;
import com.ait.grooming.repository.*;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ait.grooming.utils.maper.appointment.AppointmentMapper.allToAppointmentDto;
import static com.ait.grooming.utils.maper.appointment.AppointmentMapper.toAppointmentDto;

@Service
@Data
public class AppointmentService {
    //    private final ClientRepository clientRepository;
//    private final EmployeeRepository employeeRepository;
    private final AppointmentRepository appointmentRepository;
    private final GroomingRepository groomingRepository;
    private final PetRepository petRepository;
    private final UserRepository userRepository;

    public ResponseEntity<AppointmentResponseDto> create(AppointmentRequest appointmentRequest) {
        User client = userRepository.findByEmail(appointmentRequest.getClientEmail())
                .orElseThrow(() -> new IllegalArgumentException("client not found"));
        Grooming grooming = groomingRepository.findById(appointmentRequest.getGroomingId())
                .orElseThrow(() -> new IllegalArgumentException("grooming service not found"));
        Pet pet = petRepository.findById(getFirstPetId(client))
                .orElseThrow(() -> new IllegalArgumentException("pet not found"));
//        Pet pet = petRepository.findById(appointmentRequest.getPetId())
//                .orElseThrow(() -> new IllegalArgumentException("pet not found"));
        Appointment appointment = new Appointment();
        appointment.setClient(client);
        appointment.setGroomingService(grooming);
        appointment.setPet(pet);
        appointment.setDateTimeStart(appointmentRequest.getDateTimeStart());
       // appointment.setDateTimeEnd(appointmentRequest.getDateTimeEnd());
        appointment.setStatus("scheduled");
        appointmentRepository.save(appointment);
        return ResponseEntity.ok(toAppointmentDto(appointment));
    }
    public Integer getFirstPetId(User user) {
        List<Pet> pets = user.getPets();

        if (pets != null && !pets.isEmpty()) {
            Pet firstPet = pets.get(0);
            return firstPet.getId();
        } else {
            //doto Exception
            return null;
        }
    }

    public AppointmentResponseDto getById(Integer id) {
        return toAppointmentDto(appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("appointment not found")));
    }

    public List<AppointmentResponseDto> getAll() {
        return allToAppointmentDto(appointmentRepository.findAll());
    }
}
