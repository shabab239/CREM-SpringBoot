package com.shabab.RealEstateManagementSystem.core.repository;

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
public interface PaymentScheduleRepository extends JpaRepository<PaymentSchedule, Long> {

    Optional<List<PaymentSchedule>> findAllByCompanyId(Long companyId);

    Optional<List<PaymentSchedule>> findAllByDateBetweenAndCompanyId(Date fromDate, Date toDate, Long companyId);

    Optional<PaymentSchedule> findByIdAndCompanyId(Long id, Long companyId);

    Optional<List<PaymentSchedule>> findAllByConstructionStage_IdAndCompanyId(Long stageId, Long companyId);
}
