package com.shabab.RealEstateManagementSystem.core.repository;

import com.shabab.RealEstateManagementSystem.core.model.Payment;
import com.shabab.RealEstateManagementSystem.core.model.PaymentSchedule;
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

    Optional<List<Payment>> findAllByCustomer_IdAndCompanyId(Long customer, Long companyId);

    Optional<List<Payment>> findAllByCompany_IdAndCompanyId(Long company, Long companyId);
}
