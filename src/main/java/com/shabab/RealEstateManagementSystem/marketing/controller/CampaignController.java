package com.shabab.RealEstateManagementSystem.marketing.controller;

import com.shabab.RealEstateManagementSystem.marketing.model.Campaign;
import com.shabab.RealEstateManagementSystem.marketing.service.CampaignService;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab-1281539
 * Created on: 19/11/2024
 */
@CrossOrigin
@RestController
@RequestMapping("/api/campaign")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @GetMapping("/")
    public ApiResponse getAll() {
        return campaignService.getAll();
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Campaign campaign) {
        return campaignService.save(campaign);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody Campaign campaign) {
        return campaignService.update(campaign);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return campaignService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return campaignService.deleteById(id);
    }

    @GetMapping("/status/{status}")
    public ApiResponse getByStatus(@PathVariable Campaign.CampaignStatus status) {
        return campaignService.getByStatus(status);
    }

    @GetMapping("/start-date-range")
    public ApiResponse getByStartDateRange(@RequestParam Date startDate, @RequestParam Date endDate) {
        return campaignService.getByStartDateRange(startDate, endDate);
    }

    @GetMapping("/end-date-range")
    public ApiResponse getByEndDateRange(@RequestParam Date startDate, @RequestParam Date endDate) {
        return campaignService.getByEndDateRange(startDate, endDate);
    }

    @GetMapping("/date-range")
    public ApiResponse getByDateRange(@RequestParam Date startDate, @RequestParam Date endDate) {
        return campaignService.getByDateRange(startDate, endDate);
    }

    @GetMapping("/name-date-range")
    public ApiResponse getByNameAndDateRange(@RequestParam String name, @RequestParam Date startDate, @RequestParam Date endDate) {
        return campaignService.getByNameAndDateRange(name, startDate, endDate);
    }
}

