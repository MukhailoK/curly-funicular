package com.ait.grooming.service;

import com.ait.grooming.dto.pet.PetDto;
import com.ait.grooming.dto.pet.PetRequest;
import com.ait.grooming.dto.response.ErrorResponse;
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
import com.ait.grooming.utils.AuthHelper;
import com.ait.grooming.utils.maper.pet.PetMapper;
import com.ait.grooming.utils.request.ChangePasswordRequest;
import com.ait.grooming.utils.request.auth.AuthenticationResponse;
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

import static com.ait.grooming.dto.pet.PetRequest.allToPetDto;
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
    private final AuthHelper helper;

    public ResponseEntity<ErrorResponse> changePassword(ChangePasswordRequest request, Principal connectedUser) {
        User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
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
        return ResponseEntity.ok(new ErrorResponse("Password changed"));
    }

    @Transactional
    public ResponseEntity<ErrorResponse> deleteAll() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            deleteById(user.getId());
        }
        if (userRepository.findAll().isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse("deleted"), HttpStatus.OK);
        } else throw new NotFoundException("User not found");
    }

    @Transactional
    public void deleteById(Integer id) {
        List<Appointment> appointments = appointmentRepository.findAllByClientId(id);
        for (Appointment appointment : appointments) {
            appointmentRepository.deleteById(appointment.getId());
        }
        petRepository.deleteAllByOwnerId(id);
        userRepository.deleteById(id);

    }

    @Transactional
    public ResponseEntity<ErrorResponse> delete(Principal connectedUser) {
        if (connectedUser != null) {
            User user = userRepository.findByEmail(connectedUser.getName()).orElseThrow(() -> new NotFoundException("User not found"));
            deleteById(user.getId());
            return new ResponseEntity<>(new ErrorResponse("deleted"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse("Unauthorized"), HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<AuthenticationResponse> register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isEmpty()) {
            try {
                User user = new User();
                user.setName(request.getName());
                user.setLastName(request.getLastName());
                user.setEmail(request.getEmail());
                user.setPhone(request.getPhone());
                user.setPassword(passwordEncoder.encode(request.getPassword()));
                user.setRole(Role.CLIENT);
                user.setRegistrationDate(LocalDate.now());
                userRepository.save(user);

                if (request.getPet() != null && !request.getPet().isEmpty()) {
                    List<PetRequest> petRequest = request.getPet();
                    List<PetDto> pets = allToPetDto(petRequest);
                    pets.forEach(petDto -> petDto.setOwnerEmail(request.getEmail()));
                    petRepository.saveAll(petMapper.allToEntity(pets));
                }
                return new ResponseEntity<>(
                        helper.generateAuthResponse(request.getEmail(), request.getPassword()),
                        HttpStatus.CREATED);
            } catch (NullPointerException e) {
                throw new IllegalArgumentException(e);
            }
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