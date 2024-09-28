package com.shabab.RealEstateManagementSystem.core.repository;

import com.shabab.RealEstateManagementSystem.core.model.Building;
import com.shabab.RealEstateManagementSystem.core.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */

public interface UnitRepository extends JpaRepository<Unit, Long> {

    Optional<List<Unit>> findAllByCompanyId(Long companyId);

    Optional<Unit> findByIdAndCompanyId(Long id, Long companyId);

    Optional<Unit> findByUnitNumberAndCompanyId(String unitNumber, Long companyId);

    Optional<List<Unit>> findAllByStatusAndCompanyId(Unit.UnitStatus unitStatus, Long companyId);

    Optional<List<Unit>> findAllByTypeAndCompanyId(Unit.UnitType unitType, Long companyId);

}
