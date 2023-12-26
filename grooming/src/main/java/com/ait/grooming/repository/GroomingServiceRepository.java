package com.ait.grooming.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroomingServiceRepository extends JpaRepository<GroomingServiceRepository, Long> {
}
