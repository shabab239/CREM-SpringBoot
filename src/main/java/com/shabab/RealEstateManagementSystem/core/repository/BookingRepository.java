package com.shabab.RealEstateManagementSystem.core.repository;

import com.shabab.RealEstateManagementSystem.core.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
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
