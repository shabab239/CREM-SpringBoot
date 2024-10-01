package com.shabab.RealEstateManagementSystem.core.controller;

import com.shabab.RealEstateManagementSystem.core.model.Payment;
import com.shabab.RealEstateManagementSystem.core.model.PaymentSchedule;
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

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody Payment payment) {
        return paymentService.update(payment);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return paymentService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return paymentService.deleteById(id);
    }

    @GetMapping("/schedules")
    public ApiResponse getAllSchedules() {
        return paymentService.getAllSchedules();
    }

    @PostMapping("/schedules/save")
    public ApiResponse saveSchedule(@Valid @RequestBody PaymentSchedule paymentSchedule) {
        return paymentService.saveSchedule(paymentSchedule);
    }

    @PutMapping("/schedules/update")
    public ApiResponse updateSchedule(@Valid @RequestBody PaymentSchedule paymentSchedule) {
        return paymentService.updateSchedule(paymentSchedule);
    }

    @GetMapping("/schedules/{id}")
    public ApiResponse getScheduleById(@PathVariable Long id) {
        return paymentService.getScheduleById(id);
    }

    @DeleteMapping("/schedules/{id}")
    public ApiResponse deleteScheduleById(@PathVariable Long id) {
        return paymentService.deleteScheduleById(id);
    }
}