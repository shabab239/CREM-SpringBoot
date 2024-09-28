package com.shabab.RealEstateManagementSystem.security.restcontroller;

import com.shabab.RealEstateManagementSystem.security.model.Company;
import com.shabab.RealEstateManagementSystem.security.service.CompanyService;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */

@CrossOrigin
@RestController
@RequestMapping("/api/company")
public class CompanyRestController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/")
    public ApiResponse getAll() {
        return companyService.getAll();
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return companyService.getById(id);
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Company company) {
        return companyService.save(company);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody Company company) {
        return companyService.update(company);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return companyService.deleteById(id);
    }

}

