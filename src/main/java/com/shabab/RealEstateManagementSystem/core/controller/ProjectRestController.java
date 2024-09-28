package com.shabab.RealEstateManagementSystem.core.controller;

import com.shabab.RealEstateManagementSystem.core.model.Project;
import com.shabab.RealEstateManagementSystem.core.service.ProjectService;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 28/09/2024
 */

@RestController("/api/project")
@CrossOrigin
public class ProjectRestController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/")
    public ApiResponse findAll() {
        return projectService.findAll();
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Project project) {
        return projectService.save(project);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody Project project) {
        return projectService.update(project);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return projectService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return projectService.deleteById(id);
    }

}
