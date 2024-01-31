package com.ait.grooming.repository;

import com.ait.grooming.model.Pet;
import com.ait.grooming.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    //    @Query("SELECT u FROM User u WHERE u.role = :roleName")
//   List<User> findByRoleName(@Param("roleName") String roleName);
//
//   List<User> findByRole(String roleName);
//   List<User> findByPhone(String phone);
//   Optional<User> findByUserName(String username);
    Optional<User> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("DELETE FROM Appointment a WHERE a.client.id = :userId")
    void deleteAppointmentsByUserId(@Param("userId") Integer userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Pet p WHERE p.owner.id = :userId")
    void deletePetsByUserId(@Param("userId") Integer userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id = :userId")
    void deleteByUserId(@Param("userId") Integer userId);
    Optional<User> findByUserName(String userName);

    Optional<User> findByName(String ownerName);
}
