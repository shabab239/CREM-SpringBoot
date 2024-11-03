package com.shabab.RealEstateManagementSystem.account.service;

import com.shabab.RealEstateManagementSystem.account.model.Account;
import com.shabab.RealEstateManagementSystem.account.model.Transaction;
import com.shabab.RealEstateManagementSystem.account.repository.AccountRepository;
import com.shabab.RealEstateManagementSystem.account.repository.TransactionRepository;
import com.shabab.RealEstateManagementSystem.account.model.Payment;
import com.shabab.RealEstateManagementSystem.account.repository.PaymentRepository;
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
 * Created on: 30/09/2024
 */

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

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

    public ApiResponse getAllByCustomerId(Long customerId) {
        ApiResponse response = new ApiResponse();
        try {
            List<Payment> payments = paymentRepository.findAllByCustomerIdAndCompanyId(
                    customerId, AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("payments", payments);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved payments");
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

    @Transactional(rollbackOn = Exception.class)
    public ApiResponse save(Payment payment) {
        ApiResponse response = new ApiResponse();
        try {
            String groupTransactionId = UUID.randomUUID().toString();

            Transaction debitTransaction = new Transaction();
            debitTransaction.setAmount(payment.getAmount());
            debitTransaction.setType(Transaction.TransactionType.DEBIT);
            debitTransaction.setAccount(payment.getCustomer().getAccount());
            debitTransaction.setGroupTransactionId(groupTransactionId);
            debitTransaction.setTransactionDate(new Date());
            debitTransaction.setParticular("Payment for " + payment.getBooking().getUnit().getName());
            debitTransaction.setCompanyId(AuthUtil.getCurrentCompanyId());
            transactionRepository.save(debitTransaction);

            Transaction creditTransaction = new Transaction();
            creditTransaction.setAmount(payment.getAmount());
            creditTransaction.setType(Transaction.TransactionType.CREDIT);
            creditTransaction.setAccount(AuthUtil.getCurrentUser().getAccount());
            creditTransaction.setGroupTransactionId(groupTransactionId);
            creditTransaction.setTransactionDate(new Date());
            creditTransaction.setParticular("Payment for " + payment.getBooking().getUnit().getName());
            creditTransaction.setCompanyId(AuthUtil.getCurrentCompanyId());
            transactionRepository.save(creditTransaction);

            Account customerAccount = payment.getBooking().getCustomer().getAccount();
            customerAccount.setBalance(customerAccount.getBalance() - payment.getAmount());
            accountRepository.save(customerAccount);

            Account companyAccount = AuthUtil.getCurrentUser().getAccount();
            companyAccount.setBalance(companyAccount.getBalance() + payment.getAmount());
            accountRepository.save(companyAccount);

            payment.setGroupTransactionId(groupTransactionId);
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

}