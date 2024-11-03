package com.shabab.RealEstateManagementSystem.account.repository;

import com.shabab.RealEstateManagementSystem.account.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<List<Payment>> findAllByCompanyId(Long companyId);

    Optional<List<Payment>> findAllByDateBetweenAndCompanyId(Date fromDate, Date toDate, Long companyId);

    Optional<Payment> findByIdAndCompanyId(Long id, Long companyId);

    Optional<List<Payment>> findAllByCustomerIdAndCompanyId(Long customerId, Long companyId);
}
