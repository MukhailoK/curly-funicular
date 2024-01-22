package com.ait.grooming.service;

import com.ait.grooming.dto.response.Response;
import com.ait.grooming.dto.user.UserDto;
import com.ait.grooming.model.Appointment;
import com.ait.grooming.model.Role;
import com.ait.grooming.model.User;
import com.ait.grooming.repository.AppointmentRepository;
import com.ait.grooming.repository.PetRepository;
import com.ait.grooming.repository.UserRepository;
import com.ait.grooming.service.exceptions.IsAlreadyExistException;
import com.ait.grooming.service.exceptions.NotFoundException;
import com.ait.grooming.service.exceptions.PasswordNotSameException;
import com.ait.grooming.service.exceptions.WrongPasswordException;
import com.ait.grooming.utils.maper.pet.PetMapper;
import com.ait.grooming.utils.request.ChangePasswordRequest;
import com.ait.grooming.utils.request.auth.RegisterRequest;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import static com.ait.grooming.utils.maper.user.UserMapper.allToUserDtos;
import static com.ait.grooming.utils.maper.user.UserMapper.toUserDto;

@Service
@Data
@Log4j2
public class UserService {

    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;
    private final PetRepository petRepository;
    private final PasswordEncoder passwordEncoder;
    private final PetMapper petMapper;

    public ResponseEntity<Response> changePassword(ChangePasswordRequest request, Principal connectedUser) {
        User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        log.info("principal: " + ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());
        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new WrongPasswordException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new PasswordNotSameException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        userRepository.save(user);
        return ResponseEntity.ok(new Response("Password changed"));
    }

    public ResponseEntity<Response> deleteAll() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            deleteById(user.getId());
        }
        if (userRepository.findAll().isEmpty()) {
            return new ResponseEntity<>(new Response("deleted"), HttpStatus.OK);
        } else throw new NotFoundException("User not found");
    }

    @Transactional
    public void deleteById(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        List<Appointment> appointments = appointmentRepository.findAllByClientId(user.getId());
        // Remove associations with appointments
        for (Appointment appointment : appointments) {
            appointmentRepository.deleteById(appointment.getId());
            // Optionally, you may want to delete the appointment or handle it based on your requirements
            // appointmentRepository.deleteById(appointment.getId());
        }
        petRepository.deleteAllByOwnerId(user.getId());
        userRepository.deleteById(user.getId());
    }

    @Transactional
    public ResponseEntity<Response> delete(Principal connectedUser) {
        User user = userRepository.findByEmail(connectedUser.getName()).orElseThrow(() -> new NotFoundException("User not found"));
        deleteById(user.getId());
        return new ResponseEntity<>(new Response("deleted"), HttpStatus.OK);

    }

    public boolean register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isEmpty()) {
            User user = new User();
            user.setName(request.getName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            user.setPhone(request.getPhone());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(Role.CLIENT);
            user.setRegistrationDate(LocalDate.now());
            userRepository.save(user);
//            user.getPets().addAll(petMapper.allToEntity(request.getPet()));
            petRepository.saveAll(petMapper.allToEntity(request.getPet()));
            return true;
        }
        throw new IsAlreadyExistException("user with this email is already exist");
    }

    public ResponseEntity<List<UserDto>> getAll() {

        return ResponseEntity.ok(allToUserDtos(userRepository.findAll()));
    }

    public ResponseEntity<UserDto> getUserByPrincipalName(String principalName) {
        User user = userRepository.findByEmail(principalName).orElseThrow(() -> new NotFoundException("User not found"));
        return ResponseEntity.ok(toUserDto(user));
    }
}