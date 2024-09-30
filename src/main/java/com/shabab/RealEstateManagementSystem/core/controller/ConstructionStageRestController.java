package com.shabab.RealEstateManagementSystem.core.controller;

import com.shabab.RealEstateManagementSystem.core.model.ConstructionStage;
import com.shabab.RealEstateManagementSystem.core.service.ConstructionStageService;
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
@RequestMapping("/api/stage")
public class ConstructionStageRestController {

    @Autowired
    private ConstructionStageService constructionStageService;

    @GetMapping("/")
    public ApiResponse findAll() {
        return constructionStageService.findAll();
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody ConstructionStage constructionStage) {
        return constructionStageService.save(constructionStage);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody ConstructionStage constructionStage) {
        return constructionStageService.update(constructionStage);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return constructionStageService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return constructionStageService.deleteById(id);
    }

}