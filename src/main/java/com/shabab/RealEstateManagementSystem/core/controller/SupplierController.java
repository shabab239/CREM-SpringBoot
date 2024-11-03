package com.shabab.RealEstateManagementSystem.core.controller;

import com.shabab.RealEstateManagementSystem.core.model.rawmaterial.Supplier;
import com.shabab.RealEstateManagementSystem.core.service.SupplierService;
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
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/")
    public ApiResponse getAll() {
        return supplierService.getAll();
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Supplier supplier) {
        return supplierService.save(supplier);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody Supplier supplier) {
        return supplierService.update(supplier);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return supplierService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return supplierService.deleteById(id);
    }
}