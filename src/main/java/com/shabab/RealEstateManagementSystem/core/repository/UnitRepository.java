package com.shabab.RealEstateManagementSystem.core.repository;

import com.shabab.RealEstateManagementSystem.core.model.construction.Building;
import com.shabab.RealEstateManagementSystem.core.model.construction.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
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

    @Query("SELECT COUNT(u) FROM Unit u WHERE u.companyId = :companyId")
    Optional<Long> countByCompanyId(@Param("companyId") Long companyId);

    @Query("SELECT COUNT(u) FROM Unit u WHERE u.floor.building.project.id = :projectId AND u.companyId = :companyId")
    Optional<Long> countByProjectIdAndCompanyId(@Param("projectId") Long projectId, @Param("companyId") Long companyId);

    @Query("SELECT COUNT(u) FROM Unit u WHERE u.status = 'SOLD' AND u.floor.building.project.id = :projectId AND u.companyId = :companyId")
    Optional<Long> countSoldByProjectIdAndCompanyId(@Param("projectId") Long projectId, @Param("companyId") Long companyId);

    @Query("SELECT COUNT(u) FROM Unit u WHERE u.status = 'AVAILABLE' AND u.companyId = :companyId")
    Optional<Long> countAvailableByCompanyId(@Param("companyId") Long companyId);

    @Query("SELECT new map(u.type as type, COUNT(u) as count) FROM Unit u WHERE u.companyId = :companyId GROUP BY u.type")
    Optional<Map<String, Object>> getUnitDistribution(@Param("companyId") Long companyId);

    @Query("SELECT SUM(u.price) FROM Unit u WHERE u.status = 'AVAILABLE' AND u.companyId = :companyId")
    Optional<Double> getInventoryValue(@Param("companyId") Long companyId);

    @Query("SELECT new map(FUNCTION('MONTH', b.bookingDate) as month, COUNT(b) as count) " +
            "FROM Booking b WHERE b.companyId = :companyId GROUP BY FUNCTION('MONTH', b.bookingDate)")
    Optional<List<Map<String, Object>>> getSalesVelocity(@Param("companyId") Long companyId);
}
