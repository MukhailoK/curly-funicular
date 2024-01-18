//package com.ait.grooming.controller;
//
//import com.ait.grooming.dto.review.ReviewDto;
//import com.ait.grooming.utils.request.ReviewRequest;
//import com.ait.grooming.service.ReviewService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/review")
//@RequiredArgsConstructor
//public class ReviewController {
//
//    private final ReviewService reviewService;
//
//    @GetMapping()
//    public ResponseEntity<List<ReviewDto>> getAllReview() {
//        return reviewService.getAll();
//    }
//
//    @GetMapping("/rating/{rating}/reviews")
//    private ResponseEntity<List<ReviewDto>> getByRating(@PathVariable Double rating) {
//        return reviewService.getByRating(rating);
//    }
//
//    @PostMapping
//    public ResponseEntity<ReviewDto> create(@RequestBody ReviewRequest request) {
//        return reviewService.create(request);
//    }
//}