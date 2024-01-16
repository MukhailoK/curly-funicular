package com.ait.grooming.dto.review;

import lombok.Data;

@Data
public class ReviewRequest {
    private Integer appointmentId;
    private Double rating;
    private String review;
}
