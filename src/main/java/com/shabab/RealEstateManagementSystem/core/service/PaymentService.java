package com.shabab.RealEstateManagementSystem.core.service;

import com.shabab.RealEstateManagementSystem.core.model.Payment;
import com.shabab.RealEstateManagementSystem.core.repository.PaymentRepository;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import com.shabab.RealEstateManagementSystem.util.AuthUtil;
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
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public ApiResponse findById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Payment payment = paymentRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (payment == null) {
                return response.error("Payment not found");
            }
            response.setData("payment", payment);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved payment");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse findAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Payment> payments = paymentRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            if (payments.isEmpty()) {
                return response.error("No payments found");
            }
            response.setData("payments", payments);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved all payments");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse save(Payment payment) {
        ApiResponse response = new ApiResponse();
        try {
            payment.setCompanyId(AuthUtil.getCurrentCompanyId());
            paymentRepository.save(payment);
            response.setData("payment", payment);
            response.setSuccessful(true);
            response.setMessage("Successfully saved payment");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse update(Payment payment) {
        ApiResponse response = new ApiResponse();
        try {
            Payment dbPayment = paymentRepository.findByIdAndCompanyId(
                    payment.getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbPayment == null) {
                return response.error("Payment not found");
            }
            payment.setCompanyId(AuthUtil.getCurrentCompanyId());
            paymentRepository.save(payment);
            response.setData("payment", payment);
            response.setSuccessful(true);
            response.setMessage("Successfully updated payment");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Payment payment = paymentRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (payment == null) {
                return response.error("Payment not found");
            }
            paymentRepository.deleteById(id);
            response.setSuccessful(true);
            response.setMessage("Successfully deleted payment");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

}