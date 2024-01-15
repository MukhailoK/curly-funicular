package com.ait.grooming.dto.discount;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DiscountDto {
    private double discountRate;
    private int totalVisit;
    private int name;
}
