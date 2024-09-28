package com.shabab.RealEstateManagementSystem.core.service;

import com.shabab.RealEstateManagementSystem.core.model.Building;
import com.shabab.RealEstateManagementSystem.core.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 28/09/2024
 */

@Service
public class BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    public Optional<Building> findBuildingById(Long id) {
        return buildingRepository.findById(id);
    }

    public List<Building> findAllBuildings() {
        return buildingRepository.findAll();
    }

    public Building saveBuilding(Building building) {
        return buildingRepository.save(building);
    }

    public void deleteBuildingById(Long id) {
        buildingRepository.deleteById(id);
    }

}
