package com.shabab.RealEstateManagementSystem.marketing.service;

import com.shabab.RealEstateManagementSystem.marketing.model.Campaign;
import com.shabab.RealEstateManagementSystem.marketing.repository.CampaignRepository;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import com.shabab.RealEstateManagementSystem.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
            Campaign campaign = campaignRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
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
            List<Campaign> campaigns = campaignRepository.findAllByCompanyId(AuthUtil.getCurrentCompanyId()).orElse(new ArrayList<>());
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
            campaign.setCompanyId(AuthUtil.getCurrentCompanyId());
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
            campaign.setCompanyId(AuthUtil.getCurrentCompanyId());
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

    public ApiResponse getByStatus(Campaign.CampaignStatus status) {
        ApiResponse response = new ApiResponse();
        try {
            List<Campaign> campaigns = campaignRepository.findAllByStatusAndCompanyId(status, AuthUtil.getCurrentCompanyId()).orElse(new ArrayList<>());
            response.setData("campaigns", campaigns);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved campaigns by status");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getByStartDateRange(Date startDate, Date endDate) {
        ApiResponse response = new ApiResponse();
        try {
            List<Campaign> campaigns = campaignRepository.findAllByStartDateBetweenAndCompanyId(startDate, endDate, AuthUtil.getCurrentCompanyId()).orElse(new ArrayList<>());
            response.setData("campaigns", campaigns);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved campaigns by start date range");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    // Get campaigns by end date range and company ID
    public ApiResponse getByEndDateRange(Date startDate, Date endDate) {
        ApiResponse response = new ApiResponse();
        try {
            List<Campaign> campaigns = campaignRepository.findAllByEndDateBetweenAndCompanyId(startDate, endDate, AuthUtil.getCurrentCompanyId()).orElse(new ArrayList<>());
            response.setData("campaigns", campaigns);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved campaigns by end date range");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getByDateRange(Date startDate, Date endDate) {
        ApiResponse response = new ApiResponse();
        try {
            List<Campaign> campaigns = campaignRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqualAndCompanyId(endDate, startDate, AuthUtil.getCurrentCompanyId()).orElse(new ArrayList<>());
            response.setData("campaigns", campaigns);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved campaigns by date range");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getByNameAndDateRange(String name, Date startDate, Date endDate) {
        ApiResponse response = new ApiResponse();
        try {
            List<Campaign> campaigns = campaignRepository.findAllByNameContainingAndStartDateBetweenAndCompanyId(name, startDate, endDate, AuthUtil.getCurrentCompanyId()).orElse(new ArrayList<>());
            response.setData("campaigns", campaigns);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved campaigns by name and date range");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }
}


