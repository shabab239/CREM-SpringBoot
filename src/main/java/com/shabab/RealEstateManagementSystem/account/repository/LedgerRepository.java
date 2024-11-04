package com.shabab.RealEstateManagementSystem.account.repository;

import com.shabab.RealEstateManagementSystem.account.model.Ledger;
import com.shabab.RealEstateManagementSystem.account.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 04/11/2024
 */
public interface LedgerRepository extends JpaRepository<Ledger, Long> {

    Optional<List<Ledger>> findAllByCompanyId(Long companyId);

    Optional<Ledger> findByIdAndCompanyId(Long id, Long companyId);

}
