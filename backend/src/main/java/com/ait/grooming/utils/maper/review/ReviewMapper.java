package com.ait.grooming.utils.maper.review;

import com.ait.grooming.dto.review.ReviewDto;
import com.ait.grooming.model.Review;

import java.util.List;
import java.util.stream.Collectors;


public class ReviewMapper {
    public static ReviewDto toDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setReview(review.getReview());
        reviewDto.setId(reviewDto.getId());
        reviewDto.setAppointment(review.getAppointment());
        reviewDto.setRating(reviewDto.getRating());
        return reviewDto;
    }

    public static List<ReviewDto> allToDto(List<Review> reviews) {
        return reviews.stream().map(ReviewMapper::toDto).collect(Collectors.toList());
    }
}
