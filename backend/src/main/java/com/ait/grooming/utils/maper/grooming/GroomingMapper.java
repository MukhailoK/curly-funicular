package com.ait.grooming.utils.maper.grooming;

import com.ait.grooming.dto.grooming.GroomingDto;
import com.ait.grooming.model.Grooming;

import java.util.List;
import java.util.stream.Collectors;


public class GroomingMapper {
    public static GroomingDto toGroomingDto(Grooming grooming) {
        GroomingDto groomingDto = new GroomingDto();
        groomingDto.setName(grooming.getName());
        groomingDto.setSize(grooming.getSize());
        groomingDto.setDescription(grooming.getDescription());
        groomingDto.setPrice(grooming.getPrice());
        groomingDto.setDurationProcedure(grooming.getDurationProcedure());
        return groomingDto;
    }

    public static List<GroomingDto> allToGroomingDto(List<Grooming> groomings) {
        return groomings.stream().map(GroomingMapper::toGroomingDto).collect(Collectors.toList());
    }
}
