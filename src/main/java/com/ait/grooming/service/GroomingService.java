package com.ait.grooming.service;

import com.ait.grooming.dto.grooming.GroomingDto;
import com.ait.grooming.model.Grooming;
import com.ait.grooming.repository.GroomingRepository;
import com.ait.grooming.service.exceptions.IsAlreadyExistException;
import com.ait.grooming.service.exceptions.NotFoundException;
import com.ait.grooming.utils.request.GroomingRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ait.grooming.utils.maper.grooming.GroomingMapper.allToGroomingDto;
import static com.ait.grooming.utils.maper.grooming.GroomingMapper.toGroomingDto;

@Service
@Log4j2
@RequiredArgsConstructor
public class GroomingService {

    private final GroomingRepository groomingRepository;

    public ResponseEntity<List<GroomingDto>> getAll() {
        List<Grooming> groomings = (groomingRepository.findAll());
        if (groomings.isEmpty()) {
            throw new NotFoundException("Groomings not found");
        }
        return new ResponseEntity<>(allToGroomingDto(groomings), HttpStatus.OK);
    }

    public ResponseEntity<GroomingDto> getById(Integer id) {
        Grooming grooming = groomingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Grooming service with id " + id + " not found"));
        log.info("grooming service by id has been sent");
        return new ResponseEntity<>(toGroomingDto(grooming), HttpStatus.OK);
    }

    public ResponseEntity<GroomingDto> create(GroomingRequestDto request) {
        Grooming grooming = new Grooming();
        grooming.setName(request.getName());
        grooming.setSize(request.getSize());
        grooming.setDescription(request.getDescription());
        grooming.setPrice(request.getPrice());
        grooming.setDurationProcedure(request.getDurationProcedure());
        if (!groomingRepository.existsByName(grooming.getName())) {
            try {
                groomingRepository.save(grooming);
                log.info("grooming service has been created");
                return new ResponseEntity<>(toGroomingDto(grooming), HttpStatus.CREATED);
            } catch (Exception e) {
                throw new IllegalArgumentException();
            }
        }
        throw new IsAlreadyExistException("Grooming service is already exist");
    }
}
