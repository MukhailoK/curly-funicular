package com.ait.grooming.dto.user;

import com.ait.grooming.dto.pet.PetDto;
import com.ait.grooming.model.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserDto {
    private String name;
    private String lastName;
    private String userName;
    private String email;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDate;
    private Role role;
//    private boolean isBlocked;
//    private DiscountDto discounts;
    private List<PetDto> pets;
}
