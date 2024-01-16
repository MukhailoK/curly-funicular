//package com.ait.grooming.utils.maper.review;
//
//import com.ait.grooming.dto.review.ReviewDto;
//import com.ait.grooming.model.Review;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static com.ait.grooming.utils.maper.appointment.AppointmentMapper.toAppointmentDto;
//
//
//public class ReviewMapper {
//
//
//    public static ReviewDto toReviewDto(Review review) {
//        ReviewDto reviewDto = new ReviewDto();
//        reviewDto.setReview(review.getReview());
//        reviewDto.setId(review.getId());
//        reviewDto.setNameClient(toAppointmentDto(review.getAppointment()).getClientDto().getName());
//        reviewDto.setRating(review.getRating());
//        return reviewDto;
//    }
//
//    public static List<ReviewDto> allToReviewDto(List<Review> reviews) {
//        return reviews.stream().map(ReviewMapper::toReviewDto).collect(Collectors.toList());
//    }
//}
