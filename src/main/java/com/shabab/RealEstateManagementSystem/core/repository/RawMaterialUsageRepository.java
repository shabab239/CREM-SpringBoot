package com.shabab.RealEstateManagementSystem.core.repository;

import com.shabab.RealEstateManagementSystem.core.model.RawMaterialOrder;
import com.shabab.RealEstateManagementSystem.core.model.RawMaterialUsage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */

public interface RawMaterialUsageRepository extends JpaRepository<RawMaterialUsage, Long> {

    Optional<List<RawMaterialUsage>> findAllByCompanyId(Long companyId);

    Optional<List<RawMaterialUsage>> findAllByStageIdAndCompanyId(Long stageId, Long companyId);

    Optional<RawMaterialUsage> findByIdAndCompanyId(Long id, Long companyId);

}
