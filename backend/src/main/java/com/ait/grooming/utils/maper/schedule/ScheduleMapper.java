package com.ait.grooming.utils.maper.schedule;

import com.ait.grooming.dto.schedule.ScheduleDto;
import com.ait.grooming.model.Schedule;

import java.util.List;

public class ScheduleMapper {
    public static ScheduleDto toScheduleDto(Schedule schedule) {
        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setDayOfWeek(schedule.getDayOfWeek());
        scheduleDto.setStartTime(schedule.getStartTime());
        scheduleDto.setEndTime(schedule.getEndTime());
        return scheduleDto;
    }

    public static List<ScheduleDto> allToScheduleDto(List<Schedule> schedules) {
        return schedules.stream().map(ScheduleMapper::toScheduleDto).toList();
    }
}
