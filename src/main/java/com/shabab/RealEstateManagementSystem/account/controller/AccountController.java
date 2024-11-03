package com.shabab.RealEstateManagementSystem.account.controller;

import com.shabab.RealEstateManagementSystem.account.model.Account;
import com.shabab.RealEstateManagementSystem.account.service.AccountService;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 02/10/2024
 */

@CrossOrigin
@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return accountService.getById(id);
    }

    @GetMapping("/")
    public ApiResponse getAll() {
        return accountService.getAll();
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Account account) {
        return accountService.save(account);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody Account account) {
        return accountService.update(account);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return accountService.deleteById(id);
    }

    @GetMapping("/company/{companyId}")
    public ApiResponse getAllByCompanyId(@PathVariable Long companyId) {
        return accountService.getAllByCompanyId(companyId);
    }

    @GetMapping("/company/{companyId}/account/{id}")
    public ApiResponse getByIdAndCompanyId(@PathVariable Long id, @PathVariable Long companyId) {
        return accountService.getByIdAndCompanyId(id, companyId);
    }
}