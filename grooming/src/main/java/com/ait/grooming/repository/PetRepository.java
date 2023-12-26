package com.ait.grooming.repository;

import com.ait.grooming.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
