package com.shabab.RealEstateManagementSystem.core.controller;

import com.shabab.RealEstateManagementSystem.core.model.Unit;
import com.shabab.RealEstateManagementSystem.core.service.UnitService;
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
@RequestMapping("/api/unit")
public class UnitRestController {

    @Autowired
    private UnitService unitService;

    @GetMapping("/")
    public ApiResponse findAll() {
        return unitService.findAll();
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Unit unit) {
        return unitService.save(unit);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody Unit unit) {
        return unitService.update(unit);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return unitService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return unitService.deleteById(id);
    }
}