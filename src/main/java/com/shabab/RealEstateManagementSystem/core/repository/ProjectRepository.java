package com.shabab.RealEstateManagementSystem.core.repository;

import com.shabab.RealEstateManagementSystem.core.model.Building;
import com.shabab.RealEstateManagementSystem.core.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<List<Project>> findAllByCompanyId(Long companyId);

    Optional<Project> findByIdAndCompanyId(Long id, Long companyId);

    Optional<Project> findByNameAndCompanyId(String name, Long companyId);

    Optional<List<Project>> findAllByCompanyId(Long projectId, Long companyId);

}
