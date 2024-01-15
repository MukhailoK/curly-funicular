package com.ait.grooming.repository;

import com.ait.grooming.model.Appointment;
import com.ait.grooming.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    Optional<List<Review>> getAllByRatingGreaterThanEqual(Double rating);

    boolean existsByAppointment(Appointment appointment);
}
