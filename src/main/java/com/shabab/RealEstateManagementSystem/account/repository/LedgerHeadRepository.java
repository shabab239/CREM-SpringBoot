package com.shabab.RealEstateManagementSystem.account.repository;

import com.shabab.RealEstateManagementSystem.account.model.LedgerHead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 04/11/2024
 */
public interface LedgerHeadRepository extends JpaRepository<LedgerHead, Long> {

    Optional<List<LedgerHead>> findAllByCompanyId(Long companyId);

    Optional<LedgerHead> findByIdAndCompanyId(Long id, Long companyId);

    Optional<LedgerHead> findByNameAndCompanyId(String name, Long companyId);

}
