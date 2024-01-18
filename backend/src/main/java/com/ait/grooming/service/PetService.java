package com.ait.grooming.service;

import com.ait.grooming.model.Breed;
import com.ait.grooming.model.Pet;
import com.ait.grooming.model.User;
import com.ait.grooming.repository.BreedRepository;
import com.ait.grooming.repository.PetRepository;
import com.ait.grooming.repository.UserRepository;
import com.ait.grooming.service.exceptions.IsAlreadyExistException;
import com.ait.grooming.service.exceptions.NotFoundException;
import com.ait.grooming.utils.request.PetRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import static com.ait.grooming.utils.maper.pet.PetMapper.toPetDto;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final BreedRepository breedRepository;
    private final UserRepository userRepository;

    public ResponseEntity<?> createPet(PetRequest requestDto, Principal connectedUser) {
        if (!petRepository.existsByName(requestDto.getName())) {
            Breed breedById = breedRepository.findById(requestDto.getBreedId())
                    .orElseThrow(() -> new NotFoundException("Breed with id " + requestDto.getBreedId() + " not found!"));

            User userById = userRepository.findByEmail(connectedUser.getName())
                    .orElseThrow(() -> new NotFoundException("User with email " + connectedUser.getName() + " not found!"));

            Pet pet = new Pet();
            pet.setName(requestDto.getName());
            //  pet.setOwner(requestDto.getOwner());
            pet.setOwner(userById);
//            pet.setType(Pet.PetType.valueOf(requestDto.getType().toUpperCase()));
            //  pet.setBreed(requestDto.getBreed());
            pet.setBreed(breedById);
            //   pet.setPhotoUrl(requestDto.getPhotoUrl());
            pet.setSpecial_notes(requestDto.getSpecialNotes());
            petRepository.save(pet);
            return ResponseEntity.ok(toPetDto(pet));

        }
        throw new IsAlreadyExistException("Pet is already exist");
    }

    public ResponseEntity<?> getAllBreed() {
        List<Breed> breeds = breedRepository.findAll();
        if (breeds.isEmpty()) {
            throw new NotFoundException("Breeds not found");
        } else
            return ResponseEntity.ok(breeds);

    }

    public ResponseEntity<?> findByPetName(String petName, Principal connectedUser) {
        User owner = userRepository.findByEmail(connectedUser.getName())
                .orElseThrow(() -> new NotFoundException("User not found"));
        List<Pet> pets = petRepository
                .findAllByOwner(owner)
                .orElseThrow(() -> new NotFoundException("pets not found"));
        Pet pet = pets.stream()
                .filter(p -> p.getName().equals(petName))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Pet with name " + petName + " not found"));
        return ResponseEntity.ok(toPetDto(pet));
    }

//    public ResponseEntity<?> getAllTypes() {
//        List<String> petTypes = List.of(Arrays.toString(Pet.PetType.values()));
//        return ResponseEntity.ok(petTypes.stream().map(String::toLowerCase));
//    }
}