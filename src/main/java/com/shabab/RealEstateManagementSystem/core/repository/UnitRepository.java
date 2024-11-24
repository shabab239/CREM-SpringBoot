package com.shabab.RealEstateManagementSystem.core.repository;

import com.shabab.RealEstateManagementSystem.core.model.construction.Building;
import com.shabab.RealEstateManagementSystem.core.model.construction.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */

public interface UnitRepository extends JpaRepository<Unit, Long>, JpaSpecificationExecutor<Unit> {

    Optional<List<Unit>> findAllByCompanyId(Long companyId);

    Optional<Unit> findByIdAndCompanyId(Long id, Long companyId);

    Optional<Unit> findByNameAndCompanyId(String name, Long companyId);

    Optional<List<Unit>> findAllByStatusAndCompanyId(Unit.UnitStatus unitStatus, Long companyId);

    Optional<List<Unit>> findAllByTypeAndCompanyId(Unit.UnitType unitType, Long companyId);

    Optional<List<Unit>> findAllByFloor_BuildingAndStatusAndCompanyId(Building building, Unit.UnitStatus unitStatus, Long companyId);

    Optional<List<Unit>> findAllByFloor_Building_TypeAndStatusAndCompanyId(Building.BuildingType buildingType, Unit.UnitStatus unitStatus, Long companyId);

}
