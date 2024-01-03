package com.ait.grooming.repository;

import com.ait.grooming.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByReview(String review);
}
