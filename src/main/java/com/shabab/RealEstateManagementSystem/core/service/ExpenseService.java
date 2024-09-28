package com.shabab.RealEstateManagementSystem.core.service;

import com.shabab.RealEstateManagementSystem.core.model.Expense;
import com.shabab.RealEstateManagementSystem.core.repository.ExpenseRepository;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import com.shabab.RealEstateManagementSystem.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 28/09/2024
 */

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public ApiResponse findById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Expense dbExpense = expenseRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbExpense == null) {
                return response.error("Expense not found");
            }
            response.setData("expense", dbExpense);
            response.setMessage("Successfully retrieved expense");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse findAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Expense> expenses = expenseRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            if (expenses.isEmpty()) {
                return response.error("No expense found");
            }
            response.setData("expenses", expenses);
            response.setMessage("Successfully retrieved all expenses");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse save(Expense expense) {
        ApiResponse response = new ApiResponse();
        try {
            expense.setCompanyId(AuthUtil.getCurrentCompanyId());
            expenseRepository.save(expense);
            response.setData("expense", expense);
            response.setMessage("Successfully saved expense");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse update(Expense expense) {
        ApiResponse response = new ApiResponse();
        try {
            Expense dbExpense = expenseRepository.findByIdAndCompanyId(
                    expense.getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbExpense == null) {
                return response.error("Expense not found");
            }
            expense.setCompanyId(AuthUtil.getCurrentCompanyId());
            expenseRepository.save(expense);
            response.setData("expense", expense);
            response.setMessage("Successfully updated expense");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Expense expense = expenseRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (expense == null) {
                return response.error("Expense not found");
            }
            expenseRepository.deleteById(id);
            response.setMessage("Successfully deleted expense");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

}
