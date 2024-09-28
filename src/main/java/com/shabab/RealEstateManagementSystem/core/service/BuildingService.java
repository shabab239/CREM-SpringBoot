package com.shabab.RealEstateManagementSystem.core.service;

import com.shabab.RealEstateManagementSystem.core.model.Building;
import com.shabab.RealEstateManagementSystem.core.model.Project;
import com.shabab.RealEstateManagementSystem.core.repository.BuildingRepository;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import com.shabab.RealEstateManagementSystem.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public ApiResponse findById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Building dbBuilding = buildingRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbBuilding == null) {
                return response.error("Building not found");
            }
            response.setData("building", dbBuilding);
            response.setMessage("Successfully retrieved building");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }


    public ApiResponse findAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Building> buildings = buildingRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            if (buildings.isEmpty()) {
                return response.error("No building found");
            }
            response.setData("buildings", buildings);
            response.setMessage("Successfully retrieved all buildings");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse save(Building building) {
        ApiResponse response = new ApiResponse();
        try {
            building.setCompanyId(AuthUtil.getCurrentCompanyId());
            buildingRepository.save(building);
            response.setData("building", building);
            response.setMessage("Successfully saved building");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse update(Building building) {
        ApiResponse response = new ApiResponse();
        try {
            Building dbBuilding = buildingRepository.findByIdAndCompanyId(
                    building.getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbBuilding == null) {
                return response.error("Building not found");
            }
            building.setCompanyId(AuthUtil.getCurrentCompanyId());
            buildingRepository.save(building);
            response.setData("building", building);
            response.setMessage("Successfully updated building");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Building building = buildingRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (building == null) {
                return response.error("Building not found");
            }
            buildingRepository.deleteById(id);
            response.setMessage("Successfully deleted building");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

}
