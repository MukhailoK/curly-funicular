package com.ait.grooming.repository;

import com.ait.grooming.model.Grooming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroomingRepository extends JpaRepository<Grooming, Integer> {
    boolean existsByName(String name);
}
