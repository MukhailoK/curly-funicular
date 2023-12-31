package com.ait.grooming.service;

import com.ait.grooming.dto.grooming.GroomingDto;
import com.ait.grooming.dto.grooming.GroomingRequestDto;

import com.ait.grooming.model.Grooming;
import com.ait.grooming.repository.GroomingRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ait.grooming.utils.maper.grooming.GroomingMapper.allToGroomingDto;
import static com.ait.grooming.utils.maper.grooming.GroomingMapper.toGroomingDto;

@Service
@RequiredArgsConstructor
public class GroomingService {

    private final GroomingRepository groomingRepository;

    public List<GroomingDto> getAll() {
        return allToGroomingDto(groomingRepository.findAll());
    }

    public GroomingDto getById(Long id) {
        return toGroomingDto(groomingRepository.getReferenceById(id));
    }

    public boolean create(GroomingRequestDto request) {
        Grooming grooming = new Grooming();
        grooming.setName(request.getName());
        grooming.setSize(request.getSize());
        grooming.setDescription(request.getDescription());
        grooming.setPrice(request.getPrice());
        grooming.setDurationProcedure(request.getDurationProcedure());
        if (!groomingRepository.existsById(grooming.getId())) {
            groomingRepository.save(grooming);
            return true;
        } else {
            return false;
        }
    }
}
