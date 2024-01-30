package com.ait.grooming.service;

import com.ait.grooming.dto.pet.PetDto;
import com.ait.grooming.dto.response.Response;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

import static com.ait.grooming.utils.maper.pet.PetMapper.allToPetDto;
import static com.ait.grooming.utils.maper.pet.PetMapper.toPetDto;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final BreedRepository breedRepository;
    private final UserRepository userRepository;

    public ResponseEntity<PetDto> createPet(PetRequest requestDto, Principal connectedUser) {
        User user = userRepository.findByEmail(connectedUser.getName())
                .orElseThrow(() -> new NotFoundException("User with email " + connectedUser.getName() + " not found!"));

        List<Pet> pets = petRepository.findAllByOwner(user).get();
        if (pets.stream().noneMatch(pet -> pet.getName().equals(requestDto.getName()))) {
            Breed breedById = breedRepository.findById(requestDto.getBreedId())
                    .orElseThrow(() -> new NotFoundException("Breed with id " + requestDto.getBreedId() + " not found!"));

            Pet pet = new Pet();
            pet.setName(requestDto.getName());
            pet.setOwner(user);
            pet.setBreed(breedById);
            pet.setSpecial_notes(requestDto.getSpecialNotes());
            petRepository.save(pet);
            return ResponseEntity.ok(toPetDto(pet));

        } else
            throw new IsAlreadyExistException("Pet is already exist");
    }

    public ResponseEntity<List<Breed>> getAllBreed() {
        List<Breed> breeds = breedRepository.findAll();
        if (breeds.isEmpty()) {
            throw new NotFoundException("Breeds not found");
        } else
            return ResponseEntity.ok(breeds);

    }

    public ResponseEntity<PetDto> findByPetName(String petName, Principal connectedUser) {
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

    public ResponseEntity<List<PetDto>> findAllByPetName(String petName) {
        List<Pet> pets = petRepository
                .findAllByName(petName)
                .orElseThrow(() -> new NotFoundException("pets not found"));
        return ResponseEntity.ok(allToPetDto(pets));
    }

    public ResponseEntity<Response> delete(String petName, Principal connectedUser) {
        List<Pet> pets = petRepository.findAllByOwner(userRepository.findByEmail(connectedUser.getName()).orElseThrow()).orElseThrow();
        for (Pet pet : pets) {
            if (pet.getName().equals(petName)) {
                petRepository.delete(pet);
                return new ResponseEntity<>(new Response("deleted"), HttpStatus.OK);
            }
        }
        throw new NotFoundException("pet not found");
    }

//    public ResponseEntity<?> getAllTypes() {
//        List<String> petTypes = List.of(Arrays.toString(Pet.PetType.values()));
//        return ResponseEntity.ok(petTypes.stream().map(String::toLowerCase));
//    }
}