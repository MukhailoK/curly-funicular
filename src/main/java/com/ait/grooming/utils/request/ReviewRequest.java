package com.ait.grooming.utils.request;

import lombok.Data;

@Data
public class ReviewRequest {
    private Integer appointmentId;
    private Double rating;
    private String review;
}
