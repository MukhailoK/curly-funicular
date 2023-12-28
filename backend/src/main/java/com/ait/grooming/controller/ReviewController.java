package com.ait.grooming.controller;

import com.ait.grooming.dto.review.ReviewDto;
import com.ait.grooming.dto.review.ReviewRequestDto;
import com.ait.grooming.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping()
    public List<ReviewDto> getAllReview() {
        return reviewService.getAll();
    }

    @GetMapping("/{id}")
    private ReviewDto getById(@PathVariable Long id) {
        return reviewService.getById(id);
    }

    @PostMapping("/new")
    public boolean create(@RequestBody ReviewRequestDto request) {
        return reviewService.create(request);
    }
}