package com.ait.grooming.repository;

import com.ait.grooming.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findAllByClientId(Integer id);

    boolean existsByDateTimeStart(LocalDateTime dateTimeStart);
}
