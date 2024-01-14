package com.ait.grooming.service;

import com.ait.grooming.model.Pet;
import com.ait.grooming.model.Role;
import com.ait.grooming.model.User;
import com.ait.grooming.repository.ClientRepository;
import com.ait.grooming.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ClientService {

    private final ClientRepository clientRepository;
    private final PetRepository petRepository;

    public List<User> getAll() {
        return clientRepository.findAll()
                .stream()
                .filter(user -> user.getRole().equals(Role.CLIENT))
                .collect(Collectors.toList());
    }

    public List<Pet> getAllPetByClientId(Integer id, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        User user1 = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
        log.info("User " + user1.getEmail() + " found");
        return petRepository.findAllByOwner(user1).orElseThrow(() -> new RuntimeException("Pets by Owner " + user1.getName() + " not found"));
    }
}
