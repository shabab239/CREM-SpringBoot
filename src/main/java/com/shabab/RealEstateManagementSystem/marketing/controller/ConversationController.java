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

    @GetMapping("/all")
    public ApiResponse getAll() {
        return conversationService.getAll();
    }

    @GetMapping("/getAllByLeadId")
    public ApiResponse getAllByLeadId(@RequestParam Long leadId) {
        return conversationService.getAllByLeadId(leadId);
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Conversation conversation) {
        return conversationService.save(conversation);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody Conversation conversation) {
        return conversationService.update(conversation);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return conversationService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return conversationService.deleteById(id);
    }

    @GetMapping("/date-range")
    public ApiResponse getByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return conversationService.getByDateRange(startDate, endDate);
    }

    @GetMapping("/customer/{customerId}")
    public ApiResponse getByCustomerId(@PathVariable Long customerId) {
        return conversationService.getByCustomerId(customerId);
    }

    @GetMapping("/employee/{employeeId}")
    public ApiResponse getByEmployeeId(@PathVariable Long employeeId) {
        return conversationService.getByEmployeeId(employeeId);
    }

    @GetMapping("/lead/{leadId}")
    public ApiResponse getByLeadId(@PathVariable Long leadId) {
        return conversationService.getByLeadId(leadId);
    }
}


