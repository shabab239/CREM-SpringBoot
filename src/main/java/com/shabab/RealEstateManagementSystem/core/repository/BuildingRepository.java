package com.shabab.RealEstateManagementSystem.core.repository;

import com.shabab.RealEstateManagementSystem.core.model.construction.Building;
import com.shabab.RealEstateManagementSystem.core.model.construction.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

    Optional<List<Building>> findAllByTypeAndCompanyId(Building.BuildingType type, Long companyId);

    @Query("""
            select b from Building b
            join Floor f on b.id = f.building.id
            join Unit u on f.id = u.floor.id
            where b.companyId = :companyId and u.type = :type
            """)
    Optional<List<Building>> findAllByUnitType(Unit.UnitType type, Long companyId);

}
