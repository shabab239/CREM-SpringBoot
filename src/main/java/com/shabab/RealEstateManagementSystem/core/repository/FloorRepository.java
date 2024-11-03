package com.shabab.RealEstateManagementSystem.core.repository;

import com.shabab.RealEstateManagementSystem.core.model.construction.Floor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */

public interface FloorRepository extends JpaRepository<Floor, Long> {

    Optional<List<Floor>> findAllByCompanyId(Long companyId);

    Optional<Floor> findByIdAndCompanyId(Long id, Long companyId);

    Optional<List<Floor>> findAllByBuilding_IdAndCompanyId(Long buildingId, Long companyId);

}
