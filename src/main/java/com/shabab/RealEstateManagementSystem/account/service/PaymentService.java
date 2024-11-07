package com.shabab.RealEstateManagementSystem.account.service;

import com.shabab.RealEstateManagementSystem.account.model.Account;
import com.shabab.RealEstateManagementSystem.account.model.BookingPayment;
import com.shabab.RealEstateManagementSystem.account.model.Transaction;
import com.shabab.RealEstateManagementSystem.account.repository.AccountRepository;
import com.shabab.RealEstateManagementSystem.account.repository.TransactionRepository;
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

    // BookingPayment methods
    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            BookingPayment bookingPayment = paymentRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (bookingPayment == null) {
                return response.error("BookingPayment not found");
            }
            response.setData("bookingPayment", bookingPayment);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved bookingPayment");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getAllByCustomerId(Long customerId) {
        ApiResponse response = new ApiResponse();
        try {
            List<BookingPayment> bookingPayments = paymentRepository.findAllByCustomerIdAndCompanyId(
                    customerId, AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("bookingPayments", bookingPayments);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved bookingPayments");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<BookingPayment> bookingPayments = paymentRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("bookingPayments", bookingPayments);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved bookingPayments");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    @Transactional(rollbackOn = Exception.class)
    public ApiResponse save(BookingPayment bookingPayment) {
        ApiResponse response = new ApiResponse();
        try {
            String groupTransactionId = UUID.randomUUID().toString();

            /*Transaction debitTransaction = new Transaction();
            debitTransaction.setAmount(bookingPayment.getAmount());
            debitTransaction.setType(Transaction.TransactionType.DEBIT);
            debitTransaction.setAccount(bookingPayment.getCustomer().getAccount());
            debitTransaction.setGroupTransactionId(groupTransactionId);
            debitTransaction.setDate(new Date());
            debitTransaction.setParticular("BookingPayment for " + bookingPayment.getBooking().getUnit().getName());
            debitTransaction.setCompanyId(AuthUtil.getCurrentCompanyId());
            transactionRepository.save(debitTransaction);

            Transaction creditTransaction = new Transaction();
            creditTransaction.setAmount(bookingPayment.getAmount());
            creditTransaction.setType(Transaction.TransactionType.CREDIT);
            creditTransaction.setAccount(AuthUtil.getCurrentUser().getAccount());
            creditTransaction.setGroupTransactionId(groupTransactionId);
            creditTransaction.setDate(new Date());
            creditTransaction.setParticular("BookingPayment for " + bookingPayment.getBooking().getUnit().getName());
            creditTransaction.setCompanyId(AuthUtil.getCurrentCompanyId());
            transactionRepository.save(creditTransaction);

            Account customerAccount = bookingPayment.getBooking().getCustomer().getAccount();
            customerAccount.setBalance(customerAccount.getBalance() - bookingPayment.getAmount());
            accountRepository.save(customerAccount);

            Account companyAccount = AuthUtil.getCurrentUser().getAccount();
            companyAccount.setBalance(companyAccount.getBalance() + bookingPayment.getAmount());
            accountRepository.save(companyAccount);

            bookingPayment.setGroupTransactionId(groupTransactionId);*/
            bookingPayment.setCompanyId(AuthUtil.getCurrentCompanyId());
            paymentRepository.save(bookingPayment);
            response.setData("bookingPayment", bookingPayment);
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
            BookingPayment dbBookingPayment = paymentRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbBookingPayment == null) {
                return response.error("BookingPayment not found");
            }
            paymentRepository.delete(dbBookingPayment);
            response.setSuccessful(true);
            response.success("Deleted Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

}