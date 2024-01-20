package com.ait.grooming.controller;

import com.ait.grooming.dto.grooming.GroomingDto;
import com.ait.grooming.service.GroomingService;
import com.ait.grooming.utils.request.GroomingRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grooming")
@RequiredArgsConstructor
public class GroomingController {

    private final GroomingService groomingService;

    @GetMapping()
    public ResponseEntity<List<GroomingDto>> getAllGrooming() {
        return groomingService.getAll();
    }

    @GetMapping("/{id}")
    private ResponseEntity<GroomingDto> getById(@PathVariable Integer id) {
        return groomingService.getById(id);
    }

    @PostMapping
    public ResponseEntity<GroomingDto> create(@RequestBody GroomingRequestDto request) {

        return groomingService.create(request);
    }
}