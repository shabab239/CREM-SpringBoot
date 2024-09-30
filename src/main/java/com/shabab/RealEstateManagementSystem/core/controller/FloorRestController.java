package com.shabab.RealEstateManagementSystem.core.controller;

import com.shabab.RealEstateManagementSystem.core.model.Floor;
import com.shabab.RealEstateManagementSystem.core.service.FloorService;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 28/09/2024
 */

@CrossOrigin
@RestController
@RequestMapping("/api/floor")
public class FloorRestController {

    @Autowired
    private FloorService floorService;

    @GetMapping("/")
    public ApiResponse findAll() {
        return floorService.findAll();
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Floor floor) {
        return floorService.save(floor);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody Floor floor) {
        return floorService.update(floor);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return floorService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return floorService.deleteById(id);
    }
}