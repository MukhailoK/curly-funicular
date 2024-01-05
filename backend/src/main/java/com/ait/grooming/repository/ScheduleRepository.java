package com.ait.grooming.repository;

import com.ait.grooming.model.Schedule;
import com.ait.grooming.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByMaster(User master);

    @Query("SELECT s FROM Schedule s WHERE s.master.id = :masterId")
    List<Schedule> getAllByMasterId(@Param("masterId") Long masterId);
}
