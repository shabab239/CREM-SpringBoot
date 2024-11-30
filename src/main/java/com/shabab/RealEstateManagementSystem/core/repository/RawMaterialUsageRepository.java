package com.shabab.RealEstateManagementSystem.core.repository;

import com.shabab.RealEstateManagementSystem.core.model.rawmaterial.RawMaterialUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
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

    @Query("SELECT u FROM RawMaterialUsage u " +
            "WHERE u.stage.building.project.id = :projectId " +
            "AND u.entryDate BETWEEN :startDate AND :endDate " +
            "AND u.companyId = :companyId")
    Optional<List<RawMaterialUsage>> findByProjectIdAndDateBetweenAndCompanyId(
            @Param("projectId") Long projectId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("companyId") Long companyId
    );

}
