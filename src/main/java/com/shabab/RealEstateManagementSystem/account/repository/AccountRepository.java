package com.shabab.RealEstateManagementSystem.account.repository;

import com.shabab.RealEstateManagementSystem.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 02/10/2024
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<List<Account>> findAllByCompanyId(Long companyId);

    Optional<Account> findByIdAndCompanyId(Long id, Long companyId);
}