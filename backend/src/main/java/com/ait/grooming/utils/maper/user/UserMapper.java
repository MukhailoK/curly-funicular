package com.ait.grooming.utils.maper.user;


//import com.ait.grooming.dto.employee.EmployeeDto;

import com.ait.grooming.dto.user.UserDto;
import com.ait.grooming.model.User;
import com.ait.grooming.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ait.grooming.utils.maper.pet.PetMapper.allToPetDto;

@RequiredArgsConstructor
@Component
public class UserMapper {



    public static UserDto toUserDto(User employee) {
        UserDto userDto = new UserDto();
        userDto.setName(employee.getName());
        userDto.setLastName(employee.getLastName());
        userDto.setEmail(employee.getEmail());
        userDto.setPhone(employee.getPhone());
        userDto.setRegistrationDate(employee.getRegistrationDate());
        userDto.setRole(employee.getRole());
        userDto.setPets(allToPetDto(employee.getPets()));
        return userDto;
    }

    public static List<UserDto> allToUserDtos(List<User> employees) {
        return employees.stream().map(UserMapper::toUserDto).toList();
    }
}

