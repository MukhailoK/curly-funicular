package com.ait.grooming.repository;

import com.ait.grooming.model.Appointment;
import com.ait.grooming.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    boolean existsByReview(String review);

    boolean existsByAppointment(Appointment appointment);
}
