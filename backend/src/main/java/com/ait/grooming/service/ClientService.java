package com.ait.grooming.service;

import com.ait.grooming.model.Pet;
import com.ait.grooming.model.User;
import com.ait.grooming.repository.ClientRepository;
import com.ait.grooming.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final PetRepository petRepository;

    public List<User> getAll() {
        return clientRepository.findAll()
                .stream()
                .filter(user -> user.getRole().equals(User.Role.CLIENT))
                .collect(Collectors.toList());
    }

    public List<Pet> getAllPetByClientId(Long id) {
        return petRepository.findAll();
    }
}
