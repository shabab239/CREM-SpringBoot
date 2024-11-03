package com.shabab.RealEstateManagementSystem.core.repository;

import com.shabab.RealEstateManagementSystem.core.model.worker.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */

public interface WorkerRepository extends JpaRepository<Worker, Long> {

    Optional<List<Worker>> findAllByCompanyId(Long companyId);

    Optional<Worker> findByIdAndCompanyId(Long id, Long companyId);

}
