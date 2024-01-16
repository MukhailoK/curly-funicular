//package com.ait.grooming.utils.maper.discount;
//
//import com.ait.grooming.dto.discount.DiscountDto;
//import com.ait.grooming.model.Discount;
//
//import java.util.List;
//
//public class DiscountMapper {
//
//    public static DiscountDto toDiscountDto(Discount discount) {
//        DiscountDto discountDto = new DiscountDto();
//        discountDto.setDiscountRate(discount.getDiscountRate());
//        discountDto.setTotalVisit(discount.getTotalVisits());
//        discountDto.setName(discount.getName());
//        return discountDto;
//    }
//
//    public static List<DiscountDto> allToDiscountDto(List<Discount> discounts) {
//        return discounts.stream().map(DiscountMapper::toDiscountDto).toList();
//    }
//}
