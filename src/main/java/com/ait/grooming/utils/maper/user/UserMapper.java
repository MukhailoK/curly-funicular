package com.ait.grooming.utils.maper.user;



import com.ait.grooming.dto.user.UserDto;
import com.ait.grooming.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ait.grooming.utils.maper.pet.PetMapper.allToPetDto;

@RequiredArgsConstructor
@Component
public class UserMapper {


    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setRegistrationDate(user.getRegistrationDate());
        userDto.setRole(user.getRole());
        userDto.setUserName(user.getUserName());
        userDto.setPets(allToPetDto(user.getPets()));
        return userDto;
    }

    public static List<UserDto> allToUserDtos(List<User> users) {
        return users.stream().map(UserMapper::toUserDto).toList();
    }
}

