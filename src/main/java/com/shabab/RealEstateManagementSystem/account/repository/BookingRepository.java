package com.shabab.RealEstateManagementSystem.account.repository;

import com.shabab.RealEstateManagementSystem.account.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<List<Booking>> findAllByCompanyId(Long companyId);

    Optional<Booking> findByIdAndCompanyId(Long id, Long companyId);

    Optional<Booking> findByUnitIdAndCompanyId(Long unitId, Long companyId);

    Optional<List<Booking>> findAllByCustomerIdAndCompanyId(Long customerId, Long companyId);
}
