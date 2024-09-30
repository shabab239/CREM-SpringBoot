package com.shabab.RealEstateManagementSystem.core.service;

import com.shabab.RealEstateManagementSystem.core.model.RawMaterialStock;
import com.shabab.RealEstateManagementSystem.core.repository.RawMaterialStockRepository;
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
public class RawMaterialStockService {

    @Autowired
    private RawMaterialStockRepository rawMaterialStockRepository;

    public ApiResponse findById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            RawMaterialStock rawMaterialStock = rawMaterialStockRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (rawMaterialStock == null) {
                return response.error("Raw material stock not found");
            }
            response.setData("rawMaterialStock", rawMaterialStock);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved raw material stock");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse findAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<RawMaterialStock> rawMaterialStocks = rawMaterialStockRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());

            if (rawMaterialStocks.isEmpty()) {
                return response.error("No raw material stocks found");
            }
            response.setData("rawMaterialStocks", rawMaterialStocks);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved raw material stocks");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse save(RawMaterialStock rawMaterialStock) {
        ApiResponse response = new ApiResponse();
        try {
            rawMaterialStock.setCompanyId(AuthUtil.getCurrentCompanyId());
            rawMaterialStockRepository.save(rawMaterialStock);
            response.setData("rawMaterialStock", rawMaterialStock);
            response.setSuccessful(true);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse update(RawMaterialStock rawMaterialStock) {
        ApiResponse response = new ApiResponse();
        try {
            RawMaterialStock dbRawMaterialStock = rawMaterialStockRepository.findByIdAndCompanyId(
                    rawMaterialStock.getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbRawMaterialStock == null) {
                return response.error("Raw material stock not found");
            }
            rawMaterialStock.setCompanyId(AuthUtil.getCurrentCompanyId());
            rawMaterialStockRepository.save(rawMaterialStock);
            response.setData("rawMaterialStock", rawMaterialStock);
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
            RawMaterialStock dbRawMaterialStock = rawMaterialStockRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbRawMaterialStock == null) {
                return response.error("Raw material stock not found");
            }
            rawMaterialStockRepository.delete(dbRawMaterialStock);
            response.setSuccessful(true);
            response.success("Deleted Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }
}