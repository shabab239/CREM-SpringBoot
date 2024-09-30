package com.shabab.RealEstateManagementSystem.core.service;

import com.shabab.RealEstateManagementSystem.core.model.RawMaterial;
import com.shabab.RealEstateManagementSystem.core.repository.RawMaterialRepository;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import com.shabab.RealEstateManagementSystem.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 30/09/2024
 */

@Service
public class RawMaterialService {

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    public ApiResponse findById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            RawMaterial rawMaterial = rawMaterialRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (rawMaterial == null) {
                return response.error("Raw material not found");
            }
            response.setData("rawMaterial", rawMaterial);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved raw material");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse findAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<RawMaterial> rawMaterials = rawMaterialRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());

            if (rawMaterials.isEmpty()) {
                return response.error("No raw materials found");
            }
            response.setData("rawMaterials", rawMaterials);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved raw materials");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse save(RawMaterial rawMaterial) {
        ApiResponse response = new ApiResponse();
        try {
            rawMaterial.setCompanyId(AuthUtil.getCurrentCompanyId());
            rawMaterialRepository.save(rawMaterial);
            response.setData("rawMaterial", rawMaterial);
            response.setSuccessful(true);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse update(RawMaterial rawMaterial) {
        ApiResponse response = new ApiResponse();
        try {
            RawMaterial dbRawMaterial = rawMaterialRepository.findByIdAndCompanyId(
                    rawMaterial.getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbRawMaterial == null) {
                return response.error("Raw material not found");
            }
            rawMaterial.setCompanyId(AuthUtil.getCurrentCompanyId());
            rawMaterialRepository.save(rawMaterial);
            response.setData("rawMaterial", rawMaterial);
            response.setSuccessful(true);
            response.success("Updated Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            RawMaterial dbRawMaterial = rawMaterialRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbRawMaterial == null) {
                return response.error("Raw material not found");
            }
            rawMaterialRepository.delete(dbRawMaterial);
            response.setSuccessful(true);
            response.success("Deleted Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }
}