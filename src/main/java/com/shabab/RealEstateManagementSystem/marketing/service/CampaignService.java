package com.shabab.RealEstateManagementSystem.marketing.service;

import com.shabab.RealEstateManagementSystem.marketing.model.Campaign;
import com.shabab.RealEstateManagementSystem.marketing.repository.CampaignRepository;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab-1281539
 * Created on: 19/11/2024
 */

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Campaign campaign = campaignRepository.findById(id).orElse(null);
            if (campaign == null) {
                return response.error("Campaign not found");
            }
            response.setData("campaign", campaign);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved campaign");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Campaign> campaigns = campaignRepository.findAll();
            response.setData("campaigns", campaigns);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved campaigns");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse save(Campaign campaign) {
        ApiResponse response = new ApiResponse();
        try {
            campaignRepository.save(campaign);
            response.setData("campaign", campaign);
            response.setSuccessful(true);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse update(Campaign campaign) {
        ApiResponse response = new ApiResponse();
        try {
            Campaign dbCampaign = campaignRepository.findById(campaign.getId()).orElse(null);
            if (dbCampaign == null) {
                return response.error("Campaign not found");
            }
            campaignRepository.save(campaign);
            response.setData("campaign", campaign);
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
            Campaign dbCampaign = campaignRepository.findById(id).orElse(null);
            if (dbCampaign == null) {
                return response.error("Campaign not found");
            }
            campaignRepository.delete(dbCampaign);
            response.setSuccessful(true);
            response.success("Deleted Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }
}

