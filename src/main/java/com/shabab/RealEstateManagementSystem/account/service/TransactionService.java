package com.shabab.RealEstateManagementSystem.account.service;

import com.shabab.RealEstateManagementSystem.account.model.Account;
import com.shabab.RealEstateManagementSystem.account.model.Transaction;
import com.shabab.RealEstateManagementSystem.account.repository.AccountRepository;
import com.shabab.RealEstateManagementSystem.account.repository.BookingRepository;
import com.shabab.RealEstateManagementSystem.account.repository.PaymentRepository;
import com.shabab.RealEstateManagementSystem.account.repository.TransactionRepository;
import com.shabab.RealEstateManagementSystem.core.model.rawmaterial.RawMaterialOrder;
import com.shabab.RealEstateManagementSystem.core.repository.RawMaterialOrderRepository;
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
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RawMaterialOrderRepository rawMaterialOrderRepository;

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

    public ApiResponse getTotalIncome() {
        ApiResponse response = new ApiResponse();
        try {
            Double totalIncome = transactionRepository.sumAmountByTypeAndCompanyId(
                    Transaction.TransactionType.INCOME, AuthUtil.getCurrentCompanyId()
            ).orElse(0.0);
            response.setData("totalIncome", totalIncome);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved total income");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getTotalExpense() {
        ApiResponse response = new ApiResponse();
        try {
            Double totalExpense = transactionRepository.sumAmountByTypeAndCompanyId(
                    Transaction.TransactionType.EXPENSE, AuthUtil.getCurrentCompanyId()
            ).orElse(0.0);
            response.setData("totalExpense", totalExpense);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved total expenses");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getTransactionsByDateRange(Date startDate, Date endDate) {
        ApiResponse response = new ApiResponse();
        try {
            List<Transaction> transactions = transactionRepository.findByDateBetweenAndCompanyId(
                    startDate, endDate, AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("transactions", transactions);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved transactions for the given date range");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getTransactionsByAccount(Long accountId) {
        ApiResponse response = new ApiResponse();
        try {
            Account account = accountRepository.findByIdAndCompanyId(
                    accountId, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (account == null) {
                return response.error("Account not found");
            }
            List<Transaction> transactions = transactionRepository.findByAccount_IdAndCompanyId(
                    accountId, AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("transactions", transactions);
            response.setData("account", account);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved transactions for the account");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getProfitAndLossStatement(Date startDate, Date endDate) {
        ApiResponse response = new ApiResponse();
        try {
            Map<String, Object> statement = new HashMap<>();

            Double bookingIncome = bookingRepository.sumPaymentsByDateRange(
                    startDate, endDate, AuthUtil.getCurrentCompanyId()
            ).orElse(0.0);

            Double materialExpenses = rawMaterialOrderRepository.sumTotalPriceByDateRange(
                    startDate,
                    endDate,
                    RawMaterialOrder.RawMaterialOrderStatus.DELIVERED,
                    AuthUtil.getCurrentCompanyId()
            ).orElse(0.0);

            Double workerPayments = transactionRepository.sumWorkerPaymentsByDateRange(
                    startDate, endDate, AuthUtil.getCurrentCompanyId()
            ).orElse(0.0);

            statement.put("income", Map.of(
                    "bookingIncome", bookingIncome,
                    "totalIncome", bookingIncome
            ));

            statement.put("expenses", Map.of(
                    "materialExpenses", materialExpenses,
                    "workerPayments", workerPayments,
                    "totalExpenses", materialExpenses + workerPayments
            ));

            statement.put("grossProfit", bookingIncome);
            statement.put("netProfit", bookingIncome - (materialExpenses + workerPayments));

            response.setData("profitAndLoss", statement);
            response.setSuccessful(true);
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getCashFlowStatement(Date startDate, Date endDate) {
        ApiResponse response = new ApiResponse();
        try {
            Map<String, Object> cashFlow = new HashMap<>();

            List<Transaction> operatingTransactions = transactionRepository
                    .findByDateBetweenAndCompanyId(startDate, endDate, AuthUtil.getCurrentCompanyId())
                    .orElse(new ArrayList<>());

            Double operatingInflow = operatingTransactions.stream()
                    .filter(t -> t.getType() == Transaction.TransactionType.INCOME)
                    .mapToDouble(Transaction::getAmount)
                    .sum();

            Double operatingOutflow = operatingTransactions.stream()
                    .filter(t -> t.getType() == Transaction.TransactionType.EXPENSE)
                    .mapToDouble(Transaction::getAmount)
                    .sum();

            cashFlow.put("operatingActivities", Map.of(
                    "inflow", operatingInflow,
                    "outflow", operatingOutflow,
                    "netCashFlow", operatingInflow - operatingOutflow
            ));

            response.setData("cashFlow", cashFlow);
            response.setSuccessful(true);
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public static String generateTransactionId() {
        return UUID.randomUUID().toString();
    }
}