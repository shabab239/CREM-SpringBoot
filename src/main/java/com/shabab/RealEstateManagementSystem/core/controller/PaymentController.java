package com.shabab.RealEstateManagementSystem.core.controller;

import com.shabab.RealEstateManagementSystem.core.model.Payment;
import com.shabab.RealEstateManagementSystem.core.service.PaymentService;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 30/09/2024
 */

@CrossOrigin
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/")
    public ApiResponse getAll() {
        return paymentService.getAll();
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Payment payment) {
        return paymentService.save(payment);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return paymentService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return paymentService.deleteById(id);
    }

    @GetMapping("/customer/{customerId}")
    public ApiResponse getAllByCustomerId(@PathVariable Long customerId) {
        return paymentService.getAllByCustomerId(customerId);
    }
}