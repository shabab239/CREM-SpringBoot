package com.shabab.RealEstateManagementSystem.marketing.controller;

import com.shabab.RealEstateManagementSystem.marketing.model.Campaign;
import com.shabab.RealEstateManagementSystem.marketing.service.CampaignService;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}

