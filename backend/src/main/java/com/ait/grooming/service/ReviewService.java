package com.ait.grooming.service;

import com.ait.grooming.dto.review.ReviewDto;
import com.ait.grooming.dto.review.ReviewRequestDto;
import com.ait.grooming.model.Review;
import com.ait.grooming.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ait.grooming.utils.maper.review.ReviewMapper.allToDto;
import static com.ait.grooming.utils.maper.review.ReviewMapper.toDto;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<ReviewDto> getAll() {
        return allToDto(reviewRepository.findAll());
    }

    public ReviewDto getById(Long id) {
        return toDto(reviewRepository.getReferenceById(id));
    }

    public boolean create(ReviewRequestDto request) {
        Review review = new Review();
        review.setReview(request.getReview());
        review.setAppointment(request.getAppointment());
        review.setRating(request.getRating());
        if (!reviewRepository.existsById(review.getReviewId())) {
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }
}
