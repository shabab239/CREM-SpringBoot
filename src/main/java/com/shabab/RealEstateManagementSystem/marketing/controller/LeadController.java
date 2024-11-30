package com.shabab.RealEstateManagementSystem.marketing.controller;

import com.shabab.RealEstateManagementSystem.marketing.model.Lead;
import com.shabab.RealEstateManagementSystem.marketing.service.LeadService;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab-1281539
 * Created on: 20/11/2024
 */

@CrossOrigin
@RestController
@RequestMapping("/api/lead")
public class LeadController {

    @Autowired
    private LeadService leadService;

    @GetMapping("/")
    public ApiResponse getAll() {
        return leadService.getAll();
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return leadService.getById(id);
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Lead lead) {
        return leadService.save(lead);
    }

    @PostMapping("/convertToCustomer/{id}")
    public ApiResponse convertToCustomer(@PathVariable Long id) {
        return leadService.convertToCustomer(id);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody Lead lead) {
        return leadService.update(lead);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return leadService.deleteById(id);
    }

    @GetMapping("/status/{status}")
    public ApiResponse getByStatus(@PathVariable Lead.LeadStatus status) {
        return leadService.getByStatus(status);
    }

    @GetMapping("/campaign/{campaignId}")
    public ApiResponse getByCampaignId(@PathVariable Long campaignId) {
        return leadService.getByCampaignId(campaignId);
    }

}
