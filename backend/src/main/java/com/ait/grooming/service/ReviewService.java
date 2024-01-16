package com.ait.grooming.service;

import com.ait.grooming.dto.review.ReviewDto;
import com.ait.grooming.dto.review.ReviewRequest;
import com.ait.grooming.model.Appointment;
import com.ait.grooming.model.Review;
import com.ait.grooming.repository.AppointmentRepository;
import com.ait.grooming.repository.ReviewRepository;
import com.ait.grooming.service.exceptions.IsAlreadyExistException;
import com.ait.grooming.service.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ait.grooming.utils.maper.review.ReviewMapper.allToReviewDto;
import static com.ait.grooming.utils.maper.review.ReviewMapper.toReviewDto;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final AppointmentRepository appointmentRepository;

    public ResponseEntity<List<ReviewDto>> getAll() {
        List<ReviewDto> reviewDtos = allToReviewDto(reviewRepository.findAll());
        if (reviewDtos.isEmpty()) {
            throw new NotFoundException("Reviews not found");
        } else
            return ResponseEntity.ok(reviewDtos);
    }

    public ResponseEntity<List<ReviewDto>> getByRating(Double rating) {
        return ResponseEntity.ok(allToReviewDto(reviewRepository
                .getAllByRatingGreaterThanEqual(rating).orElseThrow(() -> new NotFoundException("Review with rating greater or equal" + rating + " not found"))));
    }

    public ResponseEntity<ReviewDto> create(ReviewRequest request) {
        Review review = new Review();
        review.setReview(request.getReview());
        Appointment appointment = appointmentRepository.findById(request.getAppointmentId())
                .orElseThrow(() -> new NotFoundException("appointment not found"));
        review.setAppointment(appointment);
        review.setRating(request.getRating());
        System.out.println(appointment);
        if (!reviewRepository.existsByAppointment(appointment)) {
            reviewRepository.save(review);
            return ResponseEntity.ok(toReviewDto(review));
        } else {
            throw new IsAlreadyExistException("Review for this appointment is already exist");
        }
    }
}
