package com.ait.grooming.repository;

import com.ait.grooming.model.Schedule;
import com.ait.grooming.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);

    Optional<User> getAllScheduleByUserName(String username);
}
