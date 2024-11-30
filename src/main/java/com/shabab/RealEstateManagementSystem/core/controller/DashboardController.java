package com.shabab.RealEstateManagementSystem.core.controller;

import com.shabab.RealEstateManagementSystem.core.service.DashboardService;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 30/11/2024
 */

@CrossOrigin
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/admin/overview")
    public ApiResponse getAdminDashboard() {
        return dashboardService.getAdminDashboard();
    }

    @GetMapping("/manager/overview")
    public ApiResponse getManagerDashboard() {
        return dashboardService.getManagerDashboard();
    }

    @GetMapping("/sales/overview")
    public ApiResponse getSalesDashboard() {
        return dashboardService.getSalesDashboard();
    }

    @GetMapping("/customer/overview")
    public ApiResponse getCustomerDashboard() {
        return dashboardService.getCustomerDashboard();
    }

    @GetMapping("/analytics/project/{id}")
    public ApiResponse getProjectAnalytics(@PathVariable Long id) {
        return dashboardService.getProjectAnalytics(id);
    }

    @GetMapping("/analytics/sales")
    public ApiResponse getSalesAnalytics(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        return dashboardService.getSalesAnalytics(page, size);
    }

    @GetMapping("/analytics/workforce")
    public ApiResponse getWorkforceAnalytics(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
        return dashboardService.getWorkforceAnalytics(page, size);
    }
}
