package com.ait.grooming.repository;

import com.ait.grooming.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("SELECT u FROM User u WHERE u.role = :roleName")
//   List<User> findByRoleName(@Param("roleName") String roleName);

   List<User> findByRole(String roleName);
   List<User> findByPhone(String phone);
   Optional<User> findByUserName(String username);
   Optional<User> findByEmail(String email);

}
