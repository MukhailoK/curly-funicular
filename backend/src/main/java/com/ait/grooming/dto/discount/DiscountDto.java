package com.ait.grooming.dto.discount;

import com.ait.grooming.dto.client.ClientDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DiscountDto {
    private double discountRate;
    private int totalVisit;
}
