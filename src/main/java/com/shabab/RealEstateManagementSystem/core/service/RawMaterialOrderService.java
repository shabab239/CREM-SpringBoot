package com.shabab.RealEstateManagementSystem.core.service;

import com.shabab.RealEstateManagementSystem.core.model.RawMaterialOrder;
import com.shabab.RealEstateManagementSystem.core.repository.RawMaterialOrderRepository;
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
public class RawMaterialOrderService {

    @Autowired
    private RawMaterialOrderRepository rawMaterialOrderRepository;

    public ApiResponse findById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            RawMaterialOrder rawMaterialOrder = rawMaterialOrderRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (rawMaterialOrder == null) {
                return response.error("Raw material order not found");
            }
            response.setData("rawMaterialOrder", rawMaterialOrder);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved raw material order");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse findAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<RawMaterialOrder> rawMaterialOrders = rawMaterialOrderRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());

            if (rawMaterialOrders.isEmpty()) {
                return response.error("No raw material orders found");
            }
            response.setData("rawMaterialOrders", rawMaterialOrders);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved raw material orders");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse save(RawMaterialOrder rawMaterialOrder) {
        ApiResponse response = new ApiResponse();
        try {
            rawMaterialOrder.setCompanyId(AuthUtil.getCurrentCompanyId());
            rawMaterialOrderRepository.save(rawMaterialOrder);
            response.setData("rawMaterialOrder", rawMaterialOrder);
            response.setSuccessful(true);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse update(RawMaterialOrder rawMaterialOrder) {
        ApiResponse response = new ApiResponse();
        try {
            RawMaterialOrder dbRawMaterialOrder = rawMaterialOrderRepository.findByIdAndCompanyId(
                    rawMaterialOrder.getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbRawMaterialOrder == null) {
                return response.error("Raw material order not found");
            }
            rawMaterialOrder.setCompanyId(AuthUtil.getCurrentCompanyId());
            rawMaterialOrderRepository.save(rawMaterialOrder);
            response.setData("rawMaterialOrder", rawMaterialOrder);
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
            RawMaterialOrder dbRawMaterialOrder = rawMaterialOrderRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbRawMaterialOrder == null) {
                return response.error("Raw material order not found");
            }
            rawMaterialOrderRepository.delete(dbRawMaterialOrder);
            response.setSuccessful(true);
            response.success("Deleted Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }
}