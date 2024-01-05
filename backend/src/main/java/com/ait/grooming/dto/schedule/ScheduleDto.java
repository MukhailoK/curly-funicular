package com.ait.grooming.dto.schedule;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;

@Data
@RequiredArgsConstructor
public class ScheduleDto {
    private int dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
}
