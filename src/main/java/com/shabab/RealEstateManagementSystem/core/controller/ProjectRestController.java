package com.shabab.RealEstateManagementSystem.core.controller;

import com.shabab.RealEstateManagementSystem.core.model.Project;
import com.shabab.RealEstateManagementSystem.core.model.Building;
import com.shabab.RealEstateManagementSystem.core.model.Floor;
import com.shabab.RealEstateManagementSystem.core.model.Unit;
import com.shabab.RealEstateManagementSystem.core.service.ProjectService;
import com.shabab.RealEstateManagementSystem.core.service.BuildingService;
import com.shabab.RealEstateManagementSystem.core.service.FloorService;
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
@RequestMapping("/api/project")
public class ProjectRestController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private FloorService floorService;

    @Autowired
    private UnitService unitService;

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

    @GetMapping("/buildings")
    public ApiResponse findAllBuildings() {
        return buildingService.findAll();
    }

    @PostMapping("/building/save")
    public ApiResponse saveBuilding(@Valid @RequestBody Building building) {
        return buildingService.save(building);
    }

    @PutMapping("/building/update")
    public ApiResponse updateBuilding(@Valid @RequestBody Building building) {
        return buildingService.update(building);
    }

    @GetMapping("/building/{id}")
    public ApiResponse getBuildingById(@PathVariable Long id) {
        return buildingService.findById(id);
    }

    @DeleteMapping("/building/{id}")
    public ApiResponse deleteBuildingById(@PathVariable Long id) {
        return buildingService.deleteById(id);
    }

    @GetMapping("/floors")
    public ApiResponse findAllFloors() {
        return floorService.findAll();
    }

    @PostMapping("/floor/save")
    public ApiResponse saveFloor(@Valid @RequestBody Floor floor) {
        return floorService.save(floor);
    }

    @PutMapping("/floor/update")
    public ApiResponse updateFloor(@Valid @RequestBody Floor floor) {
        return floorService.update(floor);
    }

    @GetMapping("/floor/{id}")
    public ApiResponse getFloorById(@PathVariable Long id) {
        return floorService.findById(id);
    }

    @DeleteMapping("/floor/{id}")
    public ApiResponse deleteFloorById(@PathVariable Long id) {
        return floorService.deleteById(id);
    }

    @GetMapping("/unit")
    public ApiResponse findAllUnits() {
        return unitService.findAll();
    }

    @PostMapping("/unit/save")
    public ApiResponse saveUnit(@Valid @RequestBody Unit unit) {
        return unitService.save(unit);
    }

    @PutMapping("/unit/update")
    public ApiResponse updateUnit(@Valid @RequestBody Unit unit) {
        return unitService.update(unit);
    }

    @GetMapping("/unit/{id}")
    public ApiResponse getUnitById(@PathVariable Long id) {
        return unitService.findById(id);
    }

    @DeleteMapping("/unit/{id}")
    public ApiResponse deleteUnitById(@PathVariable Long id) {
        return unitService.deleteById(id);
    }
}