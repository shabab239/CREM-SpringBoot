package com.shabab.RealEstateManagementSystem.core.controller;

import com.shabab.RealEstateManagementSystem.core.model.Building;
import com.shabab.RealEstateManagementSystem.core.service.BuildingService;
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
@RequestMapping("/api/building")
public class BuildingRestController {

    @Autowired
    private BuildingService buildingService;

    @GetMapping("/")
    public ApiResponse findAll() {
        return buildingService.findAll();
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Building building) {
        return buildingService.save(building);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody Building building) {
        return buildingService.update(building);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return buildingService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return buildingService.deleteById(id);
    }
}