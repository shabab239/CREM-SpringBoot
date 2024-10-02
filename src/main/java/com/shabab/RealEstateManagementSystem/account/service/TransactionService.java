package com.shabab.RealEstateManagementSystem.account.service;

import com.shabab.RealEstateManagementSystem.account.model.Transaction;
import com.shabab.RealEstateManagementSystem.account.repository.TransactionRepository;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import com.shabab.RealEstateManagementSystem.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 02/10/2024
 */

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Transaction transaction = transactionRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (transaction == null) {
                return response.error("Transaction not found");
            }
            response.setData("transaction", transaction);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved transaction");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Transaction> transactions = transactionRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("transactions", transactions);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved transactions");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse save(Transaction transaction) {
        ApiResponse response = new ApiResponse();
        try {
            transaction.setCompanyId(AuthUtil.getCurrentCompanyId());
            transactionRepository.save(transaction);
            response.setData("transaction", transaction);
            response.setSuccessful(true);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse update(Transaction transaction) {
        ApiResponse response = new ApiResponse();
        try {
            Transaction dbTransaction = transactionRepository.findByIdAndCompanyId(
                    transaction.getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbTransaction == null) {
                return response.error("Transaction not found");
            }
            transaction.setCompanyId(AuthUtil.getCurrentCompanyId());
            transactionRepository.save(transaction);
            response.setData("transaction", transaction);
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
            Transaction dbTransaction = transactionRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbTransaction == null) {
                return response.error("Transaction not found");
            }
            transactionRepository.delete(dbTransaction);
            response.setSuccessful(true);
            response.success("Deleted Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }
}