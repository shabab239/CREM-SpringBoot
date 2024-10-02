package com.shabab.RealEstateManagementSystem.core.service;

import com.shabab.RealEstateManagementSystem.account.model.Account;
import com.shabab.RealEstateManagementSystem.account.repository.AccountRepository;
import com.shabab.RealEstateManagementSystem.core.model.Supplier;
import com.shabab.RealEstateManagementSystem.core.repository.SupplierRepository;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import com.shabab.RealEstateManagementSystem.util.AuthUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 30/09/2024
 */

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private AccountRepository accountRepository;

    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Supplier supplier = supplierRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (supplier == null) {
                return response.error("Supplier not found");
            }
            response.setData("supplier", supplier);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved supplier");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Supplier> suppliers = supplierRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("suppliers", suppliers);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved suppliers");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    @Transactional(rollbackOn = Exception.class)
    public ApiResponse save(Supplier supplier) {
        ApiResponse response = new ApiResponse();
        try {
            Account account = new Account();
            account.setName(supplier.getName() + " Cash A/C");
            account.setNumber((long) (Math.random() * 1_000_000_0000L));
            account.setBalance(0.0);
            account.setCompanyId(AuthUtil.getCurrentCompanyId());
            accountRepository.save(account);

            supplier.setAccount(account);

            supplier.setCompanyId(AuthUtil.getCurrentCompanyId());
            supplierRepository.save(supplier);
            response.setData("supplier", supplier);
            response.setSuccessful(true);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse update(Supplier supplier) {
        ApiResponse response = new ApiResponse();
        try {
            Supplier dbSupplier = supplierRepository.findByIdAndCompanyId(
                    supplier.getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbSupplier == null) {
                return response.error("Supplier not found");
            }
            supplier.setCompanyId(AuthUtil.getCurrentCompanyId());
            supplierRepository.save(supplier);
            response.setData("supplier", supplier);
            response.setSuccessful(true);
            response.success("Updated Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Supplier dbSupplier = supplierRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbSupplier == null) {
                return response.error("Supplier not found");
            }
            supplierRepository.delete(dbSupplier);
            response.setSuccessful(true);
            response.success("Deleted Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }
}