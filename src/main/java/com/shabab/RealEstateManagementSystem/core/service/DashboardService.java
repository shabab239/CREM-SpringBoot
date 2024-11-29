package com.shabab.RealEstateManagementSystem.core.service;

import com.shabab.RealEstateManagementSystem.account.repository.AccountRepository;
import com.shabab.RealEstateManagementSystem.account.repository.BookingRepository;
import com.shabab.RealEstateManagementSystem.core.repository.BuildingRepository;
import com.shabab.RealEstateManagementSystem.core.repository.ConstructionStageRepository;
import com.shabab.RealEstateManagementSystem.core.repository.ProjectRepository;
import com.shabab.RealEstateManagementSystem.core.repository.UnitRepository;
import com.shabab.RealEstateManagementSystem.marketing.repository.CampaignRepository;
import com.shabab.RealEstateManagementSystem.marketing.repository.LeadRepository;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import com.shabab.RealEstateManagementSystem.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 30/11/2024
 */


@Service
public class DashboardService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private ConstructionStageRepository stageRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private AccountRepository accountRepository;

    public ApiResponse getAdminDashboard() {
        ApiResponse response = new ApiResponse();
        try {
            response.setData("projectCount", projectRepository.countByCompanyId(AuthUtil.getCurrentCompanyId()).orElse(0L));
            response.setData("buildingCount", buildingRepository.countByCompanyId(AuthUtil.getCurrentCompanyId()).orElse(0L));
            response.setData("unitCount", unitRepository.countByCompanyId(AuthUtil.getCurrentCompanyId()).orElse(0L));
            response.setData("bookingCount", bookingRepository.countByCompanyId(AuthUtil.getCurrentCompanyId()).orElse(0L));
            response.setData("revenue", accountRepository.getTotalRevenue(AuthUtil.getCurrentCompanyId()).orElse(0.0));
            response.setData("expenses", accountRepository.getTotalExpenses(AuthUtil.getCurrentCompanyId()).orElse(0.0));
            response.setData("leadCount", leadRepository.countByCompanyId(AuthUtil.getCurrentCompanyId()).orElse(0L));
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved admin dashboard data");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getManagerDashboard(Long projectId) {
        ApiResponse response = new ApiResponse();
        try {
            response.setData("stageCount", stageRepository.countByProjectIdAndCompanyId(projectId, AuthUtil.getCurrentCompanyId()).orElse(0L));
            response.setData("completedStages", stageRepository.countCompletedByProjectIdAndCompanyId(projectId, AuthUtil.getCurrentCompanyId()).orElse(0L));
            response.setData("unitCount", unitRepository.countByProjectIdAndCompanyId(projectId, AuthUtil.getCurrentCompanyId()).orElse(0L));
            response.setData("soldUnits", unitRepository.countSoldByProjectIdAndCompanyId(projectId, AuthUtil.getCurrentCompanyId()).orElse(0L));
            response.setData("projectProgress", projectRepository.getProjectProgress(projectId, AuthUtil.getCurrentCompanyId()).orElse(0.0));
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved manager dashboard data");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getSalesDashboard() {
        ApiResponse response = new ApiResponse();
        try {
            response.setData("availableUnits", unitRepository.countAvailableByCompanyId(AuthUtil.getCurrentCompanyId()).orElse(0L));
            response.setData("totalLeads", leadRepository.countByCompanyId(AuthUtil.getCurrentCompanyId()).orElse(0L));
            response.setData("activeLeads", leadRepository.countActiveByCompanyId(AuthUtil.getCurrentCompanyId()).orElse(0L));
            response.setData("convertedLeads", leadRepository.countConvertedByCompanyId(AuthUtil.getCurrentCompanyId()).orElse(0L));
            response.setData("recentBookings", bookingRepository.findRecentByCompanyId(AuthUtil.getCurrentCompanyId()).orElse(new ArrayList<>()));
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved sales dashboard data");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getCustomerDashboard() {
        ApiResponse response = new ApiResponse();
        try {
            Long customerId = AuthUtil.getCurrentUserId();
            response.setData("bookings", bookingRepository.findByCustomerId(customerId).orElse(new ArrayList<>()));
            response.setData("payments", bookingRepository.getPaymentHistory(customerId).orElse(new ArrayList<>()));
            response.setData("projectUpdates", stageRepository.getCustomerProjectUpdates(customerId).orElse(new ArrayList<>()));
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved customer dashboard data");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getProjectAnalytics(Long projectId) {
        ApiResponse response = new ApiResponse();
        try {
            response.setData("timelineProgress", projectRepository.getTimelineProgress(projectId, AuthUtil.getCurrentCompanyId()).orElse(new HashMap<>()));
            response.setData("stageAnalytics", stageRepository.getStageAnalytics(projectId, AuthUtil.getCurrentCompanyId()).orElse(new ArrayList<>()));
            response.setData("resourceUtilization", stageRepository.getResourceUtilization(projectId, AuthUtil.getCurrentCompanyId()).orElse(new HashMap<>()));
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved project analytics");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getSalesAnalytics(int page, int size) {
        ApiResponse response = new ApiResponse();
        try {
            response.setData("salesTrends", bookingRepository.getSalesTrends(AuthUtil.getCurrentCompanyId(), page, size).orElse(new ArrayList<>()));
            response.setData("leadConversion", leadRepository.getConversionAnalytics(AuthUtil.getCurrentCompanyId()).orElse(new HashMap<>()));
            response.setData("revenue", accountRepository.getRevenueAnalytics(AuthUtil.getCurrentCompanyId()).orElse(new HashMap<>()));
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved sales analytics");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getWorkforceAnalytics(int page, int size) {
        ApiResponse response = new ApiResponse();
        try {
            response.setData("workerUtilization", stageRepository.getWorkerUtilization(AuthUtil.getCurrentCompanyId()).orElse(new HashMap<>()));
            response.setData("attendanceStats", stageRepository.getAttendanceAnalytics(AuthUtil.getCurrentCompanyId()).orElse(new ArrayList<>()));
            response.setData("productivityMetrics", stageRepository.getProductivityMetrics(AuthUtil.getCurrentCompanyId()).orElse(new HashMap<>()));
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved workforce analytics");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }


}
