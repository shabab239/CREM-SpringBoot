package com.shabab.RealEstateManagementSystem.core.service;

import com.shabab.RealEstateManagementSystem.account.repository.AccountRepository;
import com.shabab.RealEstateManagementSystem.account.repository.BookingRepository;
import com.shabab.RealEstateManagementSystem.core.model.construction.Project;
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
import java.util.List;
import java.util.Map;

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

    public ApiResponse getManagerDashboard() {
        ApiResponse response = new ApiResponse();
        try {
            Long companyId = AuthUtil.getCurrentCompanyId();

            List<Project> projects = projectRepository.findAllByCompanyId(companyId).orElse(new ArrayList<>());

            List<Map<String, Object>> projectStats = new ArrayList<>();

            for(Project project : projects) {
                Map<String, Object> stats = new HashMap<>();
                stats.put("projectId", project.getId());
                stats.put("projectName", project.getName());
                stats.put("stageCount", stageRepository.countByProjectIdAndCompanyId(project.getId(), companyId).orElse(0L));
                stats.put("completedStages", stageRepository.countCompletedByProjectIdAndCompanyId(project.getId(), companyId).orElse(0L));
                stats.put("unitCount", unitRepository.countByProjectIdAndCompanyId(project.getId(), companyId).orElse(0L));
                stats.put("soldUnits", unitRepository.countSoldByProjectIdAndCompanyId(project.getId(), companyId).orElse(0L));
                stats.put("projectProgress", projectRepository.getProjectProgress(project.getId(), companyId).orElse(0.0));

                projectStats.add(stats);
            }

            response.setData("projects", projectStats);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved all projects stats");
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
