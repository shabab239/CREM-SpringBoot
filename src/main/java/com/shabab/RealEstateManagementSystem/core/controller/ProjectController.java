package com.shabab.RealEstateManagementSystem.core.controller;

import com.shabab.RealEstateManagementSystem.core.model.construction.Project;
import com.shabab.RealEstateManagementSystem.core.model.construction.Building;
import com.shabab.RealEstateManagementSystem.core.model.construction.Floor;
import com.shabab.RealEstateManagementSystem.core.model.construction.Unit;
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

@CrossOrigin
@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/")
    public ApiResponse getAllProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping("/save")
    public ApiResponse saveProject(@Valid @RequestBody Project project) {
        return projectService.saveProject(project);
    }

    @PutMapping("/update")
    public ApiResponse updateProject(@Valid @RequestBody Project project) {
        return projectService.updateProject(project);
    }

    @GetMapping("/{id}")
    public ApiResponse getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteProjectById(@PathVariable Long id) {
        return projectService.deleteProjectById(id);
    }

    @GetMapping("/buildings")
    public ApiResponse getAllBuildings() {
        return projectService.getAllBuildings();
    }

    @PostMapping("/building/save")
    public ApiResponse saveBuilding(@Valid @RequestBody Building building) {
        return projectService.saveBuilding(building);
    }

    @PutMapping("/building/update")
    public ApiResponse updateBuilding(@Valid @RequestBody Building building) {
        return projectService.updateBuilding(building);
    }

    @GetMapping("/building/{id}")
    public ApiResponse getBuildingById(@PathVariable Long id) {
        return projectService.getBuildingById(id);
    }

    @DeleteMapping("/building/{id}")
    public ApiResponse deleteBuildingById(@PathVariable Long id) {
        return projectService.deleteBuildingById(id);
    }

    @GetMapping("/floors")
    public ApiResponse getAllFloors() {
        return projectService.getAllFloors();
    }

    @PostMapping("/floor/save")
    public ApiResponse saveFloor(@Valid @RequestBody Floor floor) {
        return projectService.saveFloor(floor);
    }

    @PutMapping("/floor/update")
    public ApiResponse updateFloor(@Valid @RequestBody Floor floor) {
        return projectService.updateFloor(floor);
    }

    @GetMapping("/floor/{id}")
    public ApiResponse getFloorById(@PathVariable Long id) {
        return projectService.getFloorById(id);
    }

    @DeleteMapping("/floor/{id}")
    public ApiResponse deleteFloorById(@PathVariable Long id) {
        return projectService.deleteFloorById(id);
    }

    @GetMapping("/units")
    public ApiResponse getAllUnits() {
        return projectService.getAllUnits();
    }

    @PostMapping("/unit/save")
    public ApiResponse saveUnit(@Valid @RequestBody Unit unit) {
        return projectService.saveUnit(unit);
    }

    @PutMapping("/unit/update")
    public ApiResponse updateUnit(@Valid @RequestBody Unit unit) {
        return projectService.updateUnit(unit);
    }

    @GetMapping("/unit/{id}")
    public ApiResponse getUnitById(@PathVariable Long id) {
        return projectService.getUnitById(id);
    }

    @DeleteMapping("/unit/{id}")
    public ApiResponse deleteUnitById(@PathVariable Long id) {
        return projectService.deleteUnitById(id);
    }

}