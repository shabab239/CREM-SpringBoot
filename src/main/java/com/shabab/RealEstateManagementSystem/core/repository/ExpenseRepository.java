package com.shabab.RealEstateManagementSystem.core.repository;

import com.shabab.RealEstateManagementSystem.core.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Optional<List<Expense>> findAllByCompanyId(Long companyId);

    Optional<List<Expense>> findAllByDateBetweenAndCompanyId(Date fromDate, Date toDate, Long companyId);

    Optional<Expense> findByIdAndCompanyId(Long id, Long companyId);

    Optional<List<Expense>> findAllByConstructionStage_IdAndCompanyId(Long stageId, Long companyId);
}
