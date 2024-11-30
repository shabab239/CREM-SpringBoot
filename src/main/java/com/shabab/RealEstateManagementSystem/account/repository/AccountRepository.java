package com.shabab.RealEstateManagementSystem.account.repository;

import com.shabab.RealEstateManagementSystem.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 02/10/2024
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<List<Account>> findAllByCompanyId(Long companyId);

    Optional<Account> findByIdAndCompanyId(Long id, Long companyId);

    Optional<Account> findByCompany_IdAndCompanyId(Long id, Long companyId);

    Optional<Account> findBySupplier_IdAndCompanyId(Long supplierId, Long companyId);

    Optional<Account> findByUser_IdAndCompanyId(Long userId, Long companyId);

    Optional<Account> findByWorker_IdAndCompanyId(Long workerId, Long companyId);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type = 'INCOME' AND t.account.companyId = :companyId")
    Optional<Double> getTotalRevenue(@Param("companyId") Long companyId);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type = 'EXPENSE' AND t.account.companyId = :companyId")
    Optional<Double> getTotalExpenses(@Param("companyId") Long companyId);

    @Query("SELECT new map(FUNCTION('MONTH', t.date) as month, SUM(t.amount) as amount) " +
            "FROM Transaction t WHERE t.account.companyId = :companyId AND t.type = 'INCOME' " +
            "GROUP BY FUNCTION('MONTH', t.date)")
    Optional<Map<String, Object>> getRevenueAnalytics(@Param("companyId") Long companyId);

}
