package com.shabab.RealEstateManagementSystem.marketing.service;

import com.shabab.RealEstateManagementSystem.util.AuthUtil;
import org.springframework.stereotype.Service;
import com.shabab.RealEstateManagementSystem.marketing.model.Lead;
import com.shabab.RealEstateManagementSystem.marketing.repository.LeadRepository;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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
            Lead lead = leadRepository.findByIdAndCompanyId(id, AuthUtil.getCurrentCompanyId()).orElse(null);
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
            List<Lead> leads = leadRepository.findAllByCompanyId(AuthUtil.getCurrentCompanyId()).orElse(new ArrayList<>());
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
            lead.setCompanyId(AuthUtil.getCurrentCompanyId());
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
            Lead dbLead = leadRepository.findByIdAndCompanyId(lead.getId(), AuthUtil.getCurrentCompanyId()).orElse(null);
            if (dbLead == null) {
                return response.error("Lead not found");
            }
            lead.setCompanyId(AuthUtil.getCurrentCompanyId());
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
            Lead dbLead = leadRepository.findByIdAndCompanyId(id, AuthUtil.getCurrentCompanyId()).orElse(null);
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

    public ApiResponse getByStatus(Lead.LeadStatus status) {
        ApiResponse response = new ApiResponse();
        try {
            List<Lead> leads = leadRepository.findAllByStatusAndCompanyId(status, AuthUtil.getCurrentCompanyId()).orElse(new ArrayList<>());
            response.setData("leads", leads);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved leads by status");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getByCampaignId(Long campaignId) {
        ApiResponse response = new ApiResponse();
        try {
            List<Lead> leads = leadRepository.findAllByCampaignIdAndCompanyId(campaignId, AuthUtil.getCurrentCompanyId()).orElse(new ArrayList<>());
            response.setData("leads", leads);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved leads by campaign");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }
}

