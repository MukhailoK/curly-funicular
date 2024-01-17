package com.ait.grooming.service;

import com.ait.grooming.dto.user.UserDto;
import com.ait.grooming.model.Role;
import com.ait.grooming.model.User;
import com.ait.grooming.repository.UserRepository;
import com.ait.grooming.service.exceptions.IsAlreadyExistException;
import com.ait.grooming.service.exceptions.PasswordNotSameException;
import com.ait.grooming.service.exceptions.WrongPasswordException;
import com.ait.grooming.utils.request.ChangePasswordRequest;
import com.ait.grooming.utils.request.auth.RegisterRequest;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import static com.ait.grooming.utils.maper.user.UserMapper.allToUserDtos;

@Service
@Data
@Log4j2
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<String> changePassword(ChangePasswordRequest request, Principal connectedUser) {
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
        return ResponseEntity.ok("Password changed");
    }

    public boolean register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isEmpty()) {
            User user = new User();
            user.setName(request.getName());
            user.setLastName(request.getLastname());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(Role.CLIENT);
            user.setRegistrationDate(LocalDate.now());
            userRepository.save(user);

            return true;
        }
        throw new IsAlreadyExistException("user with this email is already exist");

    }

    public ResponseEntity<List<UserDto>> getAll(){

        return ResponseEntity.ok(allToUserDtos(userRepository.findAll()));
    }
}

