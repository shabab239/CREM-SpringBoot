package com.shabab.RealEstateManagementSystem.account.repository;

import com.shabab.RealEstateManagementSystem.account.model.Transaction;
import com.shabab.RealEstateManagementSystem.account.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 04/11/2024
 */
public interface VoucherRepository extends JpaRepository<Voucher, Long> {

    Optional<List<Voucher>> findAllByCompanyId(Long companyId);

    Optional<Voucher> findByIdAndCompanyId(Long id, Long companyId);

    Optional<Voucher> findByGroupTransactionIdAndTypeAndCompanyId(String groupTransactionId, Voucher.VoucherType type, Long companyId);

    Optional<List<Voucher>> findAllByGroupTransactionIdAndCompanyId(String groupTransactionId, Long companyId);
}
