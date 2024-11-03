package com.shabab.RealEstateManagementSystem.core.repository;

import com.shabab.RealEstateManagementSystem.core.model.construction.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */

public interface BuildingRepository extends JpaRepository<Building, Long> {

    Optional<List<Building>> findAllByCompanyId(Long companyId);

    Optional<Building> findByIdAndCompanyId(Long id, Long companyId);

    Optional<Building> findByNameAndCompanyId(String name, Long companyId);

    Optional<List<Building>> findAllByProject_IdAndCompanyId(Long projectId, Long companyId);

}
