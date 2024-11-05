package com.shabab.RealEstateManagementSystem.account.service;

import com.shabab.RealEstateManagementSystem.account.model.*;
import com.shabab.RealEstateManagementSystem.account.repository.*;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import com.shabab.RealEstateManagementSystem.util.AuthUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 05/11/2024
 */

@Service
public class LedgerService {

    @Autowired
    private LedgerRepository ledgerRepository;

    @Autowired
    private LedgerHeadRepository ledgerHeadRepository;

    public ApiResponse getAllLedgerHeads() {
        ApiResponse response = new ApiResponse();
        try {
            List<LedgerHead> ledgerHeads = ledgerHeadRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("ledgerHeads", ledgerHeads);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved ledger heads");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getLedgerHeadById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            LedgerHead ledgerHead = ledgerHeadRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (ledgerHead == null) {
                return response.error("Ledger Head not found");
            }
            response.setData("ledgerHead", ledgerHead);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved ledger head");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse saveLedgerHead(LedgerHead ledgerHead) {
        ApiResponse response = new ApiResponse();
        try {
            Long companyId = AuthUtil.getCurrentCompanyId();
            ledgerHead.setName(ledgerHead.getName().trim());
            ledgerHead.setCompanyId(companyId);

            if (ledgerHeadRepository.findByNameAndCompanyId(ledgerHead.getName(), companyId).isPresent()) {
                return response.error("Ledger Head with this name already exists");
            }

            ledgerHeadRepository.save(ledgerHead);
            response.setData("ledgerHead", ledgerHead);
            response.setSuccessful(true);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse updateLedgerHead(LedgerHead ledgerHead) {
        ApiResponse response = new ApiResponse();
        try {
            Long companyId = AuthUtil.getCurrentCompanyId();
            ledgerHead.setName(ledgerHead.getName().trim());
            LedgerHead dbLedgerHead = ledgerHeadRepository.findByIdAndCompanyId(
                    ledgerHead.getId(), companyId
            ).orElse(null);
            if (dbLedgerHead == null) {
                return response.error("Ledger Head not found");
            }

            LedgerHead existingLedgerHead = ledgerHeadRepository.findByNameAndCompanyId(
                    ledgerHead.getName(), companyId
            ).orElse(null);
            if (existingLedgerHead != null && !existingLedgerHead.getId().equals(ledgerHead.getId())) {
                return response.error("Ledger Head with this name already exists");
            }

            ledgerHead.setCompanyId(companyId);
            ledgerHeadRepository.save(ledgerHead);
            response.setData("ledgerHead", ledgerHead);
            response.setSuccessful(true);
            response.success("Updated Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }


}