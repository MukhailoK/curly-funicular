package com.ait.grooming.service;

import com.ait.grooming.model.User;
import com.ait.grooming.repository.ClientRepository;
import com.ait.grooming.repository.RoleRepository;
import com.ait.grooming.utils.request.UserRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final RoleRepository roleRepository;

    public ResponseEntity<Object> create(UserRegistrationRequest userRequest) {
        if (clientRepository.findByEmail(userRequest.getEmail()).isEmpty()) {
            User user = new User();
            user.setName(userRequest.getName());
            user.setLastName(userRequest.getLastName());
            user.setUserName(userRequest.getUserName());
            user.setEmail(userRequest.getEmail());
            user.setPassword(userRequest.getPassword());
            user.setAddress(userRequest.getAddress());
            user.setPhone(userRequest.getPhone());
            user.setRegistrationDate(LocalDate.now());
            user.setRole(roleRepository.findByName("CLIENT"));
            clientRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } else
            return new ResponseEntity<>("User with this email is already exist. ", HttpStatus.CONFLICT);
    }

    public ResponseEntity<Object> getUserByUserName(String userName) {
        Optional<User> user = clientRepository.findByUserName(userName);
        if (user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User with user name " + userName + " not found;", HttpStatus.NOT_FOUND);
        }
    }
}