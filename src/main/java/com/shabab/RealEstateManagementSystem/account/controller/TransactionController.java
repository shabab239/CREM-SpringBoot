package com.shabab.RealEstateManagementSystem.account.controller;

import com.shabab.RealEstateManagementSystem.account.model.Transaction;
import com.shabab.RealEstateManagementSystem.account.service.TransactionService;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 02/10/2024
 */

@CrossOrigin
@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return transactionService.getById(id);
    }

    @GetMapping("/")
    public ApiResponse getAll() {
        return transactionService.getAll();
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Transaction transaction) {
        return transactionService.save(transaction);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody Transaction transaction) {
        return transactionService.update(transaction);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return transactionService.deleteById(id);
    }

    @GetMapping("/total-income")
    public ApiResponse getTotalIncome() {
        return transactionService.getTotalIncome();
    }

    @GetMapping("/total-expense")
    public ApiResponse getTotalExpense() {
        return transactionService.getTotalExpense();
    }

    @GetMapping("/transactions-by-date-range")
    public ApiResponse getTransactionsByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date startDateObj = sdf.parse(startDate);
        Date endDateObj = sdf.parse(endDate);

        return transactionService.getTransactionsByDateRange(startDateObj, endDateObj);
    }

    @GetMapping("/transactions-by-account")
    public ApiResponse getTransactionsByAccount(@RequestParam Long accountId) {
        return transactionService.getTransactionsByAccount(accountId);
    }
}