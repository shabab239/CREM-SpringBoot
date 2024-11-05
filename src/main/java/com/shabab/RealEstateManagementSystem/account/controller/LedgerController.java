package com.shabab.RealEstateManagementSystem.account.controller;

import com.shabab.RealEstateManagementSystem.account.model.Ledger;
import com.shabab.RealEstateManagementSystem.account.model.LedgerHead;
import com.shabab.RealEstateManagementSystem.account.service.LedgerService;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 05/11/2024
 */

@CrossOrigin
@RestController
@RequestMapping("/api/ledger")
public class LedgerController {

    @Autowired
    private LedgerService ledgerService;

    /*@GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return ledgerService.getById(id);
    }

    @GetMapping("/")
    public ApiResponse getAll() {
        return ledgerService.getAll();
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Ledger ledger) {
        return ledgerService.save(ledger);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody Ledger ledger) {
        return ledgerService.update(ledger);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return ledgerService.deleteById(id);
    }*/

    @GetMapping("/ledgerHeads")
    public ApiResponse getAllLedgerHeads() {
        return ledgerService.getAllLedgerHeads();
    }

    @GetMapping("/ledgerHeads/{id}")
    public ApiResponse getLedgerHeadById(@PathVariable Long id) {
        return ledgerService.getLedgerHeadById(id);
    }

    @PostMapping("/ledgerHeads/save")
    public ApiResponse saveLedgerHead(@Valid @RequestBody LedgerHead ledgerHead) {
        return ledgerService.saveLedgerHead(ledgerHead);
    }

    @PutMapping("/ledgerHeads/update")
    public ApiResponse updateLedgerHead(@Valid @RequestBody LedgerHead ledgerHead) {
        return ledgerService.updateLedgerHead(ledgerHead);
    }

}