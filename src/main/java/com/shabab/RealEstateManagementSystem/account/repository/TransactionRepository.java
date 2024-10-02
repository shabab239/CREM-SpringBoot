package com.shabab.RealEstateManagementSystem.account.repository;

import com.shabab.RealEstateManagementSystem.account.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
