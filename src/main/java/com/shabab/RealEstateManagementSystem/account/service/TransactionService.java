package com.shabab.RealEstateManagementSystem.account.service;

import com.shabab.RealEstateManagementSystem.account.model.*;
import com.shabab.RealEstateManagementSystem.account.repository.AccountRepository;
import com.shabab.RealEstateManagementSystem.account.repository.PaymentRepository;
import com.shabab.RealEstateManagementSystem.account.repository.TransactionRepository;
import com.shabab.RealEstateManagementSystem.account.repository.VoucherRepository;
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
 * Created on: 02/10/2024
 */

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PaymentRepository paymentRepository;

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

    @Transactional(rollbackOn = Exception.class)
    public ApiResponse payWorkers(Transaction transaction) {
        ApiResponse response = new ApiResponse();
        try {
            String groupTransactionId = UUID.randomUUID().toString();

            Account workerAccount = accountRepository.findByIdAndCompanyId(
                    999L, AuthUtil.getCurrentCompanyId()
            ).orElse(null);

            if (workerAccount == null) {
                return response.error("Worker Account not found");
            }


            Transaction debitTransaction = new Transaction();
            debitTransaction.setAmount(transaction.getAmount());
            debitTransaction.setType(Transaction.TransactionType.DEBIT);
            debitTransaction.setAccount(workerAccount);
            debitTransaction.setGroupTransactionId(groupTransactionId);
            debitTransaction.setDate(new Date());
            debitTransaction.setParticular("Worker Salary");
            debitTransaction.setCompanyId(AuthUtil.getCurrentCompanyId());
            transactionRepository.save(debitTransaction);

            Transaction creditTransaction = new Transaction();
            creditTransaction.setAmount(transaction.getAmount());
            creditTransaction.setType(Transaction.TransactionType.CREDIT);
            creditTransaction.setAccount(AuthUtil.getCurrentUser().getAccount());
            creditTransaction.setGroupTransactionId(groupTransactionId);
            creditTransaction.setDate(new Date());
            creditTransaction.setParticular("Worker Salary");
            creditTransaction.setCompanyId(AuthUtil.getCurrentCompanyId());
            transactionRepository.save(creditTransaction);


            workerAccount.setBalance(workerAccount.getBalance() + transaction.getAmount());
            accountRepository.save(workerAccount);

            Account companyAccount = AuthUtil.getCurrentUser().getAccount();
            companyAccount.setBalance(companyAccount.getBalance() - transaction.getAmount());
            accountRepository.save(companyAccount);

            response.setSuccessful(true);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    @Transactional(rollbackOn = Exception.class)
    public void processTransaction(Double amount,
                                   Transaction.TransactionType transactionType,
                                   Account debitAccount,
                                   Account creditAccount,
                                   String description,
                                   Voucher.VoucherType voucherType,
                                   Booking booking) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        String groupTransactionId = generateTransactionId();

        Transaction debitTransaction = new Transaction();
        debitTransaction.setAmount(amount);
        debitTransaction.setType(Transaction.TransactionType.DEBIT);
        debitTransaction.setAccount(debitAccount);
        debitTransaction.setGroupTransactionId(groupTransactionId);
        debitTransaction.setDate(new Date());
        debitTransaction.setParticular(description);
        debitTransaction.setCompanyId(AuthUtil.getCurrentCompanyId());
        transactionRepository.save(debitTransaction);

        Transaction creditTransaction = new Transaction();
        creditTransaction.setAmount(amount);
        creditTransaction.setType(Transaction.TransactionType.CREDIT);
        creditTransaction.setAccount(creditAccount);
        creditTransaction.setGroupTransactionId(groupTransactionId);
        creditTransaction.setDate(new Date());
        creditTransaction.setParticular(description);
        creditTransaction.setCompanyId(AuthUtil.getCurrentCompanyId());
        transactionRepository.save(creditTransaction);

        if (transactionType == Transaction.TransactionType.DEBIT) {
            debitAccount.setBalance(debitAccount.getBalance() + amount);
            creditAccount.setBalance(creditAccount.getBalance() - amount);
        } else if (transactionType == Transaction.TransactionType.CREDIT) {
            debitAccount.setBalance(debitAccount.getBalance() - amount);
            creditAccount.setBalance(creditAccount.getBalance() + amount);
        }

        accountRepository.save(debitAccount);
        accountRepository.save(creditAccount);

        if (booking != null) {
            BookingPayment bookingPayment = new BookingPayment();
            bookingPayment.setAmount(amount);
            bookingPayment.setDate(new Date());
            bookingPayment.setBooking(booking);
            bookingPayment.setGroupTransactionId(groupTransactionId);
            bookingPayment.setCustomer(booking.getCustomer());
            bookingPayment.setCompanyId(AuthUtil.getCurrentCompanyId());
            paymentRepository.save(bookingPayment);
        }

        Voucher voucher = new Voucher();
        voucher.setType(voucherType);
        voucher.setGroupTransactionId(groupTransactionId);
        voucher.setAmount(amount);
        voucher.setDate(new Date());
        voucher.setDebitAccountNo(debitAccount);
        voucher.setCreditAccountNo(creditAccount);
        voucher.setCompanyId(AuthUtil.getCurrentCompanyId());

        voucherRepository.save(voucher);

    }

    public static String generateTransactionId() {
        return UUID.randomUUID().toString();
    }
}