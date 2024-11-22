package com.shabab.RealEstateManagementSystem.marketing.controller;

import com.shabab.RealEstateManagementSystem.marketing.model.Conversation;
import com.shabab.RealEstateManagementSystem.marketing.service.ConversationService;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab-1281539
 * Created on: 20/11/2024
 */
@CrossOrigin
@RestController
@RequestMapping("/api/conversation")
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    @GetMapping("/all/{companyId}")
    public ApiResponse getAll(@PathVariable Long companyId) {
        return conversationService.getAll(companyId);
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Conversation conversation) {
        return conversationService.save(conversation);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody Conversation conversation) {
        return conversationService.update(conversation, conversation.getCompanyId());
    }

    @GetMapping("/{id}/{companyId}")
    public ApiResponse getById(@PathVariable Long id, @PathVariable Long companyId) {
        return conversationService.getById(id, companyId);
    }

    @DeleteMapping("/{id}/{companyId}")
    public ApiResponse deleteById(@PathVariable Long id, @PathVariable Long companyId) {
        return conversationService.deleteById(id, companyId);
    }

    @GetMapping("/date-range/{companyId}")
    public ApiResponse getByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @PathVariable Long companyId) {
        return conversationService.getByDateRange(startDate, endDate, companyId);
    }

    @GetMapping("/customer/{customerId}/{companyId}")
    public ApiResponse getByCustomerId(@PathVariable Long customerId, @PathVariable Long companyId) {
        return conversationService.getByCustomerId(customerId, companyId);
    }

    @GetMapping("/employee/{employeeId}/{companyId}")
    public ApiResponse getByEmployeeId(@PathVariable Long employeeId, @PathVariable Long companyId) {
        return conversationService.getByEmployeeId(employeeId, companyId);
    }

    @GetMapping("/lead/{leadId}/{companyId}")
    public ApiResponse getByLeadId(@PathVariable Long leadId, @PathVariable Long companyId) {
        return conversationService.getByLeadId(leadId, companyId);
    }
}


