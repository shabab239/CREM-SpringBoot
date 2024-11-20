package com.shabab.RealEstateManagementSystem.marketing.service;

import org.springframework.stereotype.Service;
import com.shabab.RealEstateManagementSystem.marketing.model.Lead;
import com.shabab.RealEstateManagementSystem.marketing.repository.LeadRepository;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab-1281539
 * Created on: 19/11/2024
 */

@Service
public class LeadService {

    @Autowired
    private LeadRepository leadRepository;

    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Lead lead = leadRepository.findById(id).orElse(null);
            if (lead == null) {
                return response.error("Lead not found");
            }
            response.setData("lead", lead);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved lead");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Lead> leads = leadRepository.findAll();
            response.setData("leads", leads);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved leads");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse save(Lead lead) {
        ApiResponse response = new ApiResponse();
        try {
            leadRepository.save(lead);
            response.setData("lead", lead);
            response.setSuccessful(true);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse update(Lead lead) {
        ApiResponse response = new ApiResponse();
        try {
            Lead dbLead = leadRepository.findById(lead.getId()).orElse(null);
            if (dbLead == null) {
                return response.error("Lead not found");
            }
            leadRepository.save(lead);
            response.setData("lead", lead);
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
            Lead dbLead = leadRepository.findById(id).orElse(null);
            if (dbLead == null) {
                return response.error("Lead not found");
            }
            leadRepository.delete(dbLead);
            response.setSuccessful(true);
            response.success("Deleted Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }
}

