package com.shabab.RealEstateManagementSystem.core.controller;

import com.shabab.RealEstateManagementSystem.core.model.construction.ConstructionStage;
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
public class ConstructionStageController {

    @Autowired
    private ConstructionStageService constructionStageService;

    @GetMapping("/")
    public ApiResponse getAll() {
        return constructionStageService.getAll();
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
        return constructionStageService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return constructionStageService.deleteById(id);
    }

    @GetMapping("/getAllStagesByBuildingId")
    public ApiResponse getAllStagesByBuildingId(@RequestParam Long buildingId) {
        return constructionStageService.getAllStagesByBuildingId(buildingId);
    }

    @GetMapping("/getAllStagesByFloorId")
    public ApiResponse getAllStagesByFloorId(@RequestParam Long floorId) {
        return constructionStageService.getAllStagesByFloorId(floorId);
    }

    @GetMapping("/getAllStagesByUnitId")
    public ApiResponse getAllStagesByUnitId(@RequestParam Long unitId) {
        return constructionStageService.getAllStagesByUnitId(unitId);
    }

    @GetMapping("/getAllWorkersByStageId")
    public ApiResponse getAllWorkersByStageId(@RequestParam Long stageId) {
        return constructionStageService.getAllWorkersByStageId(stageId);
    }

    @PostMapping("/addWorkerToStage")
    public ApiResponse addWorkerToStage(@RequestParam Long stageId, @RequestParam Long workerId) {
        return constructionStageService.addWorkerToStage(stageId, workerId);
    }

}