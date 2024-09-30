package com.shabab.RealEstateManagementSystem.core.repository;

import com.shabab.RealEstateManagementSystem.core.model.Project;
import com.shabab.RealEstateManagementSystem.core.model.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {

    Optional<List<RawMaterial>> findAllByCompanyId(Long companyId);

    Optional<RawMaterial> findByIdAndCompanyId(Long id, Long companyId);

}
