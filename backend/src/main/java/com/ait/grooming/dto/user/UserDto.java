package com.ait.grooming.dto.user;

import com.ait.grooming.dto.pet.PetDto;
import com.ait.grooming.model.Role;
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
    private LocalDate registrationDate;
    private Role role;
    private boolean isBlocked;
//    private DiscountDto discounts;
    private List<PetDto> pets;
}
