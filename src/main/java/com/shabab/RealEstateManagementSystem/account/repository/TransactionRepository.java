package com.shabab.RealEstateManagementSystem.account.repository;

import com.shabab.RealEstateManagementSystem.account.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 02/10/2024
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<List<Transaction>> findAllByCompanyId(Long companyId);

    Optional<Transaction> findByIdAndCompanyId(Long id, Long companyId);

    Optional<List<Transaction>> findAllByAccountIdAndCompanyId(Long accountId, Long companyId);

    Optional<Transaction> findByGroupTransactionIdAndTypeAndCompanyId(String groupTransactionId, Transaction.TransactionType type, Long companyId);

    Optional<List<Transaction>> findAllByGroupTransactionIdAndCompanyId(String groupTransactionId, Long companyId);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type = :type AND t.companyId = :companyId")
    Optional<Double> sumAmountByTypeAndCompanyId(@Param("type") Transaction.TransactionType type, @Param("companyId") Long companyId);

    Optional<List<Transaction>> findByDateBetweenAndCompanyId(Date startDate, Date endDate, Long companyId);

    Optional<List<Transaction>> findByAccount_IdAndCompanyId(Long accountId, Long companyId);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.account.id = :accountId AND t.companyId = :companyId")
    Optional<Double> sumAmountByAccountIdAndCompanyId(
            @Param("accountId") Long accountId,
            @Param("companyId") Long companyId);
}
