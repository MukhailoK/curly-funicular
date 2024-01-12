package com.ait.grooming.service;

import com.ait.grooming.model.User;
import com.ait.grooming.repository.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class UserService {

    private final UserRepository userRepository;

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName).orElseThrow();
    }
}
