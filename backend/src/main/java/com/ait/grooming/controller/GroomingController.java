package com.ait.grooming.controller;

import com.ait.grooming.dto.grooming.GroomingDto;
import com.ait.grooming.dto.grooming.GroomingRequestDto;
import com.ait.grooming.service.GroomingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grooming")
@RequiredArgsConstructor
public class GroomingController {

    private final GroomingService groomingService;

    @GetMapping()
    public List<GroomingDto> getAllGrooming() {
        return groomingService.getAll();
    }

    @GetMapping("/{id}")
    private GroomingDto getById(@PathVariable Integer id) {
        return groomingService.getById(id);
    }

    @PostMapping("/new")
    public boolean create(@RequestBody GroomingRequestDto request) {

        return groomingService.create(request);
    }
}