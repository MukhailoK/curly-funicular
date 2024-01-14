package com.ait.grooming.repository;

import com.ait.grooming.model.Pet;
import com.ait.grooming.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
    boolean existsByName(String name);

    Optional<List<Pet>> findAllByOwner(User user);
}
