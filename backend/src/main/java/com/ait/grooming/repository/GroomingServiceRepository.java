package com.ait.grooming.repository;

import com.ait.grooming.model.GroomingService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroomingServiceRepository extends JpaRepository<GroomingService, Long> {
}
