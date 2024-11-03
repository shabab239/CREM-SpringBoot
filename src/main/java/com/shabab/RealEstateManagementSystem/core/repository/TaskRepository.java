package com.shabab.RealEstateManagementSystem.core.repository;

import com.shabab.RealEstateManagementSystem.core.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<List<Task>> findAllByCompanyId(Long companyId);

    Optional<Task> findByIdAndCompanyId(Long id, Long companyId);

}
