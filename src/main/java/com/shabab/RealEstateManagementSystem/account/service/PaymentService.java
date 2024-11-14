package com.shabab.RealEstateManagementSystem.account.service;

import com.shabab.RealEstateManagementSystem.account.model.Account;
import com.shabab.RealEstateManagementSystem.account.model.Booking;
import com.shabab.RealEstateManagementSystem.account.model.BookingPayment;
import com.shabab.RealEstateManagementSystem.account.model.Transaction;
import com.shabab.RealEstateManagementSystem.account.repository.AccountRepository;
import com.shabab.RealEstateManagementSystem.account.repository.BookingRepository;
import com.shabab.RealEstateManagementSystem.account.repository.TransactionRepository;
import com.shabab.RealEstateManagementSystem.account.repository.PaymentRepository;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import com.shabab.RealEstateManagementSystem.util.AuthUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private BookingRepository bookingRepository;

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
            Booking booking = bookingRepository.findByIdAndCompanyId(
                    bookingPayment.getBooking().getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (booking == null) {
                return response.error("Booking not found");
            }

            String groupTransactionId = TransactionService.generateTransactionId();

            transactionService.recordExpense(
                    bookingPayment.getAmount(),
                    "Booking Payment - " + bookingPayment.getBooking().getUnit().getName(),
                    accountService.getUserAccount(booking.getCustomer().getId()),
                    groupTransactionId,
                    Optional.empty()

            );

            transactionService.recordIncome(
                    bookingPayment.getAmount(),
                    "Booking Payment - " + bookingPayment.getBooking().getUnit().getName(),
                    accountService.getCompanyAccount(),
                    groupTransactionId,
                    Optional.empty()
            );

            bookingPayment.setGroupTransactionId(groupTransactionId);

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