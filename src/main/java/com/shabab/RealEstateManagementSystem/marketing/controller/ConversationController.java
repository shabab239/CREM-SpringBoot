package com.shabab.RealEstateManagementSystem.marketing.controller;

import com.shabab.RealEstateManagementSystem.marketing.model.Conversation;
import com.shabab.RealEstateManagementSystem.marketing.service.ConversationService;
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
@RequestMapping("/api/conversation")
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    @GetMapping("/")
    public ApiResponse getAll() {
        return conversationService.getAll();
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
}

