package com.shabab.RealEstateManagementSystem.core.service;

import com.shabab.RealEstateManagementSystem.core.model.PaymentSchedule;
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
            PaymentSchedule paymentSchedule = expenseRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (paymentSchedule == null) {
                return response.error("PaymentSchedule not found");
            }
            response.setData("paymentSchedule", paymentSchedule);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved paymentSchedule");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse findAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<PaymentSchedule> expens = expenseRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            if (expens.isEmpty()) {
                return response.error("No expense found");
            }
            response.setData("expens", expens);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved all expens");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse save(PaymentSchedule paymentSchedule) {
        ApiResponse response = new ApiResponse();
        try {
            paymentSchedule.setCompanyId(AuthUtil.getCurrentCompanyId());
            expenseRepository.save(paymentSchedule);
            response.setData("paymentSchedule", paymentSchedule);
            response.setSuccessful(true);
            response.setMessage("Successfully saved paymentSchedule");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse update(PaymentSchedule paymentSchedule) {
        ApiResponse response = new ApiResponse();
        try {
            PaymentSchedule dbPaymentSchedule = expenseRepository.findByIdAndCompanyId(
                    paymentSchedule.getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbPaymentSchedule == null) {
                return response.error("PaymentSchedule not found");
            }
            paymentSchedule.setCompanyId(AuthUtil.getCurrentCompanyId());
            expenseRepository.save(paymentSchedule);
            response.setData("paymentSchedule", paymentSchedule);
            response.setSuccessful(true);
            response.setMessage("Successfully updated paymentSchedule");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            PaymentSchedule paymentSchedule = expenseRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (paymentSchedule == null) {
                return response.error("PaymentSchedule not found");
            }
            expenseRepository.deleteById(id);
            response.setSuccessful(true);
            response.setMessage("Successfully deleted paymentSchedule");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

}
