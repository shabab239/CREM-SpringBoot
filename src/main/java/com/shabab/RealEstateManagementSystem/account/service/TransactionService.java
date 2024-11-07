package com.shabab.RealEstateManagementSystem.account.service;

import com.shabab.RealEstateManagementSystem.account.model.Account;
import com.shabab.RealEstateManagementSystem.account.model.Transaction;
import com.shabab.RealEstateManagementSystem.account.repository.AccountRepository;
import com.shabab.RealEstateManagementSystem.account.repository.PaymentRepository;
import com.shabab.RealEstateManagementSystem.account.repository.TransactionRepository;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import com.shabab.RealEstateManagementSystem.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public void recordExpense(Double amount, String particular, Account account, String groupTransactionId, Optional<Date> date) throws Exception {
        try {
            Transaction expenseTrx = new Transaction();
            expenseTrx.setAmount(amount);
            expenseTrx.setType(Transaction.TransactionType.EXPENSE);
            expenseTrx.setAccount(account);
            expenseTrx.setGroupTransactionId(groupTransactionId);
            expenseTrx.setDate(date.orElse(new Date()));
            expenseTrx.setParticular(particular);
            expenseTrx.setCompanyId(AuthUtil.getCurrentCompanyId());
            transactionRepository.save(expenseTrx);
            account.setBalance(account.getBalance() - amount);
            accountRepository.save(account);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void recordIncome(Double amount, String particular, Account account, String groupTransactionId, Optional<Date> date) throws Exception {
        try {
            Transaction incomeTrx = new Transaction();
            incomeTrx.setAmount(amount);
            incomeTrx.setType(Transaction.TransactionType.INCOME);
            incomeTrx.setAccount(account);
            incomeTrx.setGroupTransactionId(groupTransactionId);
            incomeTrx.setDate(date.orElse(new Date()));
            incomeTrx.setParticular(particular);
            incomeTrx.setCompanyId(AuthUtil.getCurrentCompanyId());
            transactionRepository.save(incomeTrx);
            account.setBalance(account.getBalance() + amount);
            accountRepository.save(account);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static String generateTransactionId() {
        return UUID.randomUUID().toString();
    }
}