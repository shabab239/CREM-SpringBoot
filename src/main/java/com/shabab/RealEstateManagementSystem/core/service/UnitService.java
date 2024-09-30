package com.shabab.RealEstateManagementSystem.core.service;

import com.shabab.RealEstateManagementSystem.core.model.Unit;
import com.shabab.RealEstateManagementSystem.core.repository.UnitRepository;
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
public class UnitService {

    @Autowired
    private UnitRepository unitRepository;

    public ApiResponse findById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Unit unit = unitRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (unit == null) {
                return response.error("Unit not found");
            }
            response.setData("unit", unit);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved unit");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse findAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Unit> units = unitRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            if (units.isEmpty()) {
                return response.error("No unit found");
            }
            response.setData("units", units);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved all units");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse save(Unit unit) {
        ApiResponse response = new ApiResponse();
        try {
            unit.setCompanyId(AuthUtil.getCurrentCompanyId());
            unitRepository.save(unit);
            response.setData("unit", unit);
            response.setSuccessful(true);
            response.setMessage("Successfully saved unit");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse update(Unit unit) {
        ApiResponse response = new ApiResponse();
        try {
            Unit dbUnit = unitRepository.findByIdAndCompanyId(
                    unit.getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbUnit == null) {
                return response.error("Unit not found");
            }
            unit.setCompanyId(AuthUtil.getCurrentCompanyId());
            unitRepository.save(unit);
            response.setData("unit", unit);
            response.setSuccessful(true);
            response.setMessage("Successfully updated unit");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Unit unit = unitRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (unit == null) {
                return response.error("Unit not found");
            }
            unitRepository.deleteById(id);
            response.setSuccessful(true);
            response.setMessage("Successfully deleted unit");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

}
