package com.shabab.RealEstateManagementSystem.account.repository;

import com.shabab.RealEstateManagementSystem.account.model.BookingPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */
public interface PaymentRepository extends JpaRepository<BookingPayment, Long> {

    Optional<List<BookingPayment>> findAllByCompanyId(Long companyId);

    Optional<List<BookingPayment>> findAllByDateBetweenAndCompanyId(Date fromDate, Date toDate, Long companyId);

    Optional<BookingPayment> findByIdAndCompanyId(Long id, Long companyId);

    Optional<List<BookingPayment>> findAllByCustomerIdAndCompanyId(Long customerId, Long companyId);
}
