package com.shabab.RealEstateManagementSystem.account.controller;

import com.shabab.RealEstateManagementSystem.account.model.Booking;
import com.shabab.RealEstateManagementSystem.account.service.BookingService;
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
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/")
    public ApiResponse getAll() {
        return bookingService.getAll();
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Booking booking) {
        return bookingService.save(booking);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody Booking booking) {
        return bookingService.update(booking);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return bookingService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return bookingService.deleteById(id);
    }

    @GetMapping("/unit/{unitId}")
    public ApiResponse getByUnitId(@PathVariable Long unitId) {
        return bookingService.getByUnitId(unitId);
    }

    @GetMapping("/customer/{customerId}")
    public ApiResponse getAllByCustomerId(@PathVariable Long customerId) {
        return bookingService.getAllByCustomerId(customerId);
    }

}