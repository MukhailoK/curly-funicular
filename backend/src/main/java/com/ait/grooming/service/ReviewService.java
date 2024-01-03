package com.ait.grooming.service;

import com.ait.grooming.dto.review.ReviewDto;
import com.ait.grooming.dto.review.ReviewRequestDto;
import com.ait.grooming.model.Appointment;
import com.ait.grooming.model.Review;
import com.ait.grooming.repository.AppointmentRepository;
import com.ait.grooming.repository.ClientRepository;
import com.ait.grooming.repository.EmployeeRepository;
import com.ait.grooming.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ait.grooming.utils.maper.review.ReviewMapper.allToReviewDto;
import static com.ait.grooming.utils.maper.review.ReviewMapper.toReviewDto;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final AppointmentRepository appointmentRepository;
    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;

    public List<ReviewDto> getAll() {
        return allToReviewDto(reviewRepository.findAll());
    }

    public ReviewDto getById(Long id) {
        return toReviewDto(reviewRepository.getReferenceById(id));
    }

    public Review create(ReviewRequestDto request) {
        Review review = new Review();
        review.setReview(request.getReview());
        Appointment appointment = appointmentRepository.findById(request.getAppointmentId()).orElseThrow();
        review.setAppointment(appointment);
        review.setRating(request.getRating());
        if (!reviewRepository.existsByReview(review.getReview())) {
            reviewRepository.save(review);
            return review;
        } else {
            return null;
        }
    }
}
