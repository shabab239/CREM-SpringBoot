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

    @GetMapping("/total-account-balance")
    public ApiResponse getTotalAccountBalance() {
        return accountService.getTotalAccountBalance();
    }

    @GetMapping("/balance-by-account-type")
    public ApiResponse getBalanceByAccountType(@RequestParam String accountType) {
        return accountService.getBalanceByAccountType(accountType);
    }

    @GetMapping("/account-summary")
    public ApiResponse getAccountSummary(@RequestParam Long accountId) {
        return accountService.getAccountSummary(accountId);
    }
}