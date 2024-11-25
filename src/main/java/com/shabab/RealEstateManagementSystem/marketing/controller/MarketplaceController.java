package com.shabab.RealEstateManagementSystem.marketing.controller;

import com.shabab.RealEstateManagementSystem.core.model.construction.Building;
import com.shabab.RealEstateManagementSystem.marketing.dto.UnitSearchDTO;
import com.shabab.RealEstateManagementSystem.marketing.service.MarketplaceService;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab-1281539
 * Created on: 19/11/2024
 */

@CrossOrigin
@RestController
@RequestMapping("/api/marketplace")
public class MarketplaceController {

    @Autowired
    private MarketplaceService marketplaceService;

    @GetMapping("/buildings")
    public ApiResponse getAllAvailableBuildings() {
        return marketplaceService.getAllAvailableBuildings();
    }

    @GetMapping("/buildings/{id}")
    public ApiResponse getBuildingWithAvailableUnits(@PathVariable Long id) {
        return marketplaceService.getBuildingWithAvailableUnits(id);
    }

    @GetMapping("/units/{id}")
    public ApiResponse getUnitDetails(@PathVariable Long id) {
        return marketplaceService.getUnitDetails(id);
    }

    @GetMapping("/units/search")
    public ApiResponse searchAvailableUnits(
            @Valid UnitSearchDTO criteria,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return marketplaceService.searchAvailableUnits(criteria, page, size);
    }

    @GetMapping("/units/building-type/{type}")
    public ApiResponse getUnitsByBuildingType(
            @PathVariable Building.BuildingType type) {
        return marketplaceService.getUnitsByBuildingType(type);
    }

}

