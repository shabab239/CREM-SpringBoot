package com.shabab.RealEstateManagementSystem.core.repository;

import com.shabab.RealEstateManagementSystem.core.model.ConstructionStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */
public interface ConstructionStageRepository extends JpaRepository<ConstructionStage, Long> {

    Optional<List<ConstructionStage>> findAllByCompanyId(Long companyId);

    Optional<ConstructionStage> findByIdAndCompanyId(Long id, Long companyId);

    @Query("""
            SELECT cs FROM ConstructionStage cs
            WHERE cs.startDate >= :startDate AND cs.endDate <= :endDate
            AND cs.companyId = :companyId
            """)
    Optional<List<ConstructionStage>> findAll(Date startDate, Date endDate, Long companyId);


    Optional<ConstructionStage> findByNameAndCompanyId(String name, Long companyId);

    Optional<ConstructionStage> findByStatusAndCompanyId(ConstructionStage.StageStatus status, Long companyId);

    Optional<List<ConstructionStage>> findAllByBuilding_IdAndCompanyId(Long projectId, Long companyId);
}
