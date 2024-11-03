package com.shabab.RealEstateManagementSystem.account.controller;

import com.shabab.RealEstateManagementSystem.account.model.Transaction;
import com.shabab.RealEstateManagementSystem.account.service.TransactionService;
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

    @GetMapping("/groupTransaction")
    public ApiResponse getAllByGroupTransactionId(@RequestParam String groupTransactionId) {
        return transactionService.getAllByGroupTransactionId(groupTransactionId);
    }

    @PostMapping("/payWorkers")
    public ApiResponse payWorkers(@Valid @RequestBody Transaction transaction) {
        return transactionService.payWorkers(transaction);
    }
}