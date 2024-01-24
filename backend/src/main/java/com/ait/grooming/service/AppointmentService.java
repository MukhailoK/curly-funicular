package com.ait.grooming.service;

import com.ait.grooming.dto.appointment.AppointmentResponseDto;
import com.ait.grooming.model.*;
import com.ait.grooming.repository.*;
import com.ait.grooming.service.exceptions.NotFoundException;
import com.ait.grooming.utils.request.AppointmentRequest;
import com.ait.grooming.utils.request.NewUserAppointmentRequest;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.ait.grooming.utils.maper.appointment.AppointmentMapper.allToAppointmentDto;
import static com.ait.grooming.utils.maper.appointment.AppointmentMapper.toAppointmentDto;

@Service
@Data
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final GroomingRepository groomingRepository;
    private final PetRepository petRepository;
    private final UserRepository userRepository;
    private final BreedRepository breedRepository;

    public ResponseEntity<AppointmentResponseDto> create(AppointmentRequest request, Principal connectedUser) {
        Pet pet = new Pet();

        User client = userRepository.findByEmail(connectedUser.getName())
                .orElseThrow(() -> new IllegalArgumentException("client not found"));

        Grooming grooming = groomingRepository.findById(request.getGroomingId())
                .orElseThrow(() -> new IllegalArgumentException("grooming service not found"));
        pet = petRepository.findAllByOwner(client)
                .orElseThrow(() -> new IllegalArgumentException("pet not found"))
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("pet not found"));
        Appointment appointment = new Appointment();
        appointment.setClient(client);
        appointment.setGroomingService(grooming);
        appointment.setPet(pet);
        appointment.setDateTimeStart(request.getDateTimeStart());
        appointment.setStatus("Created");
        appointment.setDateTimeEnd(request.getDateTimeStart().plusHours(2));
        appointmentRepository.save(appointment);

        return new ResponseEntity<>(toAppointmentDto(appointment), HttpStatus.CREATED);
    }

    public ResponseEntity<AppointmentResponseDto> create(NewUserAppointmentRequest appointmentRequest) {
        User guest = new User();
        Pet pet = new Pet();
        if (userRepository.findByEmail(appointmentRequest.getEmail()).isPresent()) {
            guest = userRepository.findByEmail(appointmentRequest.getEmail())
                    .orElseThrow(() -> new NotFoundException("User not found"));
            for (Pet userPet : guest.getPets()) {
                if (userPet.getName().equals(appointmentRequest.getNameDog())) {
                    return guestRegister(appointmentRequest, guest, userPet);
                }
                pet = new Pet();
                pet.setOwner(guest);
                pet.setBreed(breedRepository.findByName(appointmentRequest.getBreed()));
                pet.setName(appointmentRequest.getNameDog());
                pet.setSpecial_notes(appointmentRequest.getSpecialNotes());
                petRepository.save(pet);
                return guestRegister(appointmentRequest, guest, pet);
            }
        } else
            guest.setName(appointmentRequest.getName());
        guest.setLastName(appointmentRequest.getLastName());
        guest.setPhone(appointmentRequest.getPhone());
        guest.setRole(Role.GUEST);
        guest.setEmail(appointmentRequest.getEmail());
        guest.setRegistrationDate(LocalDate.now());
        pet.setOwner(guest);
        pet.setBreed(breedRepository.findByName(appointmentRequest.getBreed()));
        pet.setName(appointmentRequest.getNameDog());
        pet.setSpecial_notes(appointmentRequest.getSpecialNotes());
        guest.setPets(List.of(pet));

        userRepository.save(guest);
        petRepository.save(pet);
        return guestRegister(appointmentRequest, guest, pet);

    }

    private ResponseEntity<AppointmentResponseDto> guestRegister(NewUserAppointmentRequest appointmentRequest,
                                                                 User guest,
                                                                 Pet pet) {
        Appointment appointment = new Appointment();
        appointment.setClient(guest);
        appointment.setPet(pet);
        appointment.setGroomingService(groomingRepository.findById(appointmentRequest.getGroomingId())
                .orElseThrow(() -> new NotFoundException("grooming not found")));
        appointment.setDateTimeStart(appointmentRequest.getDateTimeStart());
        appointment.setStatus("scheduled");
        appointment.setDateTimeEnd(appointmentRequest.getDateTimeStart().plusHours(2));
        appointmentRepository.save(appointment);

        return new ResponseEntity<>(toAppointmentDto(appointment), HttpStatus.CREATED);
    }

    public AppointmentResponseDto getById(Integer id) {
        return toAppointmentDto(appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("appointment not found")));
    }

    public List<AppointmentResponseDto> getAll() {
        return allToAppointmentDto(appointmentRepository.findAll());
    }

    public ResponseEntity<List<AppointmentResponseDto>> getAllByUserEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        List<Appointment> appointments = appointmentRepository.findAllByClientId(user.getId());
        return new ResponseEntity<>(allToAppointmentDto(appointments), HttpStatus.OK);
    }

    public ResponseEntity<String> delete(Integer appointmentId) {
        try {
            Appointment appointment = appointmentRepository.findById(appointmentId)
                    .orElseThrow(() -> new NotFoundException("Appointment not found with id: " + appointmentId));

            appointmentRepository.delete(appointment);
            return ResponseEntity.ok("Appointment deleted successfully");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while deleting appointment");
        }
    }
}
