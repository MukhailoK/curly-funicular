package com.ait.grooming.utils.maper.user;


//import com.ait.grooming.dto.employee.EmployeeDto;
import com.ait.grooming.dto.user.UserDto;
import com.ait.grooming.model.User;

import java.util.List;

public class UserMapper {
    public static UserDto toUserDto(User employee) {
        UserDto userDto = new UserDto();
        userDto.setName(employee.getName());
        userDto.setLastName(employee.getLastName());
        userDto.setEmail(employee.getEmail());
        userDto.setPhone(employee.getPhone());
        userDto.setRegistrationDate(employee.getRegistrationDate());

        return userDto;
    }

    private static List<UserDto> allToUserDtos(List<User> employees) {
        return employees.stream().map(UserMapper::toUserDto).toList();
    }
}

