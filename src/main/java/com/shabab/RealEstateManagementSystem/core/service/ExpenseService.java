package com.shabab.RealEstateManagementSystem.core.service;

import com.shabab.RealEstateManagementSystem.core.model.Expense;
import com.shabab.RealEstateManagementSystem.core.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 28/09/2024
 */

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Optional<List<Expense>> findAllByCompanyId(Long companyId) {
        return expenseRepository.findAllByCompanyId(companyId);
    }

    public Optional<List<Expense>> findAllByDateBetweenAndCompanyId(Date fromDate, Date toDate, Long companyId) {
        return expenseRepository.findAllByDateBetweenAndCompanyId(fromDate, toDate, companyId);
    }

    public Optional<Expense> findByIdAndCompanyId(Long id, Long companyId) {
        return expenseRepository.findByIdAndCompanyId(id, companyId);
    }

    public Optional<List<Expense>> findAllByConstructionStage_IdAndCompanyId(Long stageId, Long companyId) {
        return expenseRepository.findAllByConstructionStage_IdAndCompanyId(stageId, companyId);
    }
}
