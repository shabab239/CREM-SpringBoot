package com.shabab.RealEstateManagementSystem.core.service;

import com.shabab.RealEstateManagementSystem.core.model.Payment;
import com.shabab.RealEstateManagementSystem.core.model.PaymentSchedule;
import com.shabab.RealEstateManagementSystem.core.repository.PaymentRepository;
import com.shabab.RealEstateManagementSystem.core.repository.PaymentScheduleRepository;
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

    @Autowired
    private PaymentScheduleRepository paymentScheduleRepository;

    // Payment methods
    public ApiResponse getById(Long id) {
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

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Payment> payments = paymentRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("payments", payments);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved payments");
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
            response.success("Saved Successfully");
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
            response.success("Updated Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Payment dbPayment = paymentRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbPayment == null) {
                return response.error("Payment not found");
            }
            paymentRepository.delete(dbPayment);
            response.setSuccessful(true);
            response.success("Deleted Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    // PaymentSchedule methods
    public ApiResponse getScheduleById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            PaymentSchedule paymentSchedule = paymentScheduleRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (paymentSchedule == null) {
                return response.error("Payment schedule not found");
            }
            response.setData("paymentSchedule", paymentSchedule);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved payment schedule");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getAllSchedules() {
        ApiResponse response = new ApiResponse();
        try {
            List<PaymentSchedule> paymentSchedules = paymentScheduleRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("paymentSchedules", paymentSchedules);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved payment schedules");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse saveSchedule(PaymentSchedule paymentSchedule) {
        ApiResponse response = new ApiResponse();
        try {
            paymentSchedule.setCompanyId(AuthUtil.getCurrentCompanyId());
            paymentScheduleRepository.save(paymentSchedule);
            response.setData("paymentSchedule", paymentSchedule);
            response.setSuccessful(true);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse updateSchedule(PaymentSchedule paymentSchedule) {
        ApiResponse response = new ApiResponse();
        try {
            PaymentSchedule dbPaymentSchedule = paymentScheduleRepository.findByIdAndCompanyId(
                    paymentSchedule.getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbPaymentSchedule == null) {
                return response.error("Payment schedule not found");
            }
            paymentSchedule.setCompanyId(AuthUtil.getCurrentCompanyId());
            paymentScheduleRepository.save(paymentSchedule);
            response.setData("paymentSchedule", paymentSchedule);
            response.setSuccessful(true);
            response.success("Updated Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse deleteScheduleById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            PaymentSchedule dbPaymentSchedule = paymentScheduleRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbPaymentSchedule == null) {
                return response.error("Payment schedule not found");
            }
            paymentScheduleRepository.delete(dbPaymentSchedule);
            response.setSuccessful(true);
            response.success("Deleted Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }
}