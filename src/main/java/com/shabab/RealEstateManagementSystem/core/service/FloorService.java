package com.shabab.RealEstateManagementSystem.core.service;

import com.shabab.RealEstateManagementSystem.core.model.Floor;
import com.shabab.RealEstateManagementSystem.core.repository.FloorRepository;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import com.shabab.RealEstateManagementSystem.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 28/09/2024
 */

@Service
public class FloorService {

    @Autowired
    private FloorRepository floorRepository;

    public ApiResponse findById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Floor floor = floorRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (floor == null) {
                return response.error("Floor not found");
            }
            response.setData("floor", floor);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved floor");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse findAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Floor> floors = floorRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            if (floors.isEmpty()) {
                return response.error("No floor found");
            }
            response.setData("floors", floors);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved all floors");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse save(Floor floor) {
        ApiResponse response = new ApiResponse();
        try {
            floor.setCompanyId(AuthUtil.getCurrentCompanyId());
            floorRepository.save(floor);
            response.setData("floor", floor);
            response.setSuccessful(true);
            response.setMessage("Successfully saved floor");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse update(Floor floor) {
        ApiResponse response = new ApiResponse();
        try {
            Floor dbFloor = floorRepository.findByIdAndCompanyId(
                    floor.getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbFloor == null) {
                return response.error("Floor not found");
            }
            floor.setCompanyId(AuthUtil.getCurrentCompanyId());
            floorRepository.save(floor);
            response.setData("floor", floor);
            response.setSuccessful(true);
            response.setMessage("Successfully updated floor");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Floor floor = floorRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (floor == null) {
                return response.error("Floor not found");
            }
            floorRepository.deleteById(id);
            response.setSuccessful(true);
            response.setMessage("Successfully deleted floor");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

}