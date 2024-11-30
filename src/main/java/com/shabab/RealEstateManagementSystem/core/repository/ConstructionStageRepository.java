package com.shabab.RealEstateManagementSystem.core.repository;

import com.shabab.RealEstateManagementSystem.core.model.construction.ConstructionStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;
import java.util.Map;
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

    Optional<List<ConstructionStage>> findAllByBuilding_IdAndCompanyId(Long buildingId, Long companyId);

    Optional<List<ConstructionStage>> findAllByBuildingIdAndCompanyId(Long buildingId, Long companyId);

    Optional<List<ConstructionStage>> findAllByFloorIdAndCompanyId(Long floorId, Long companyId);

    Optional<List<ConstructionStage>> findAllByUnitIdAndCompanyId(Long unitId, Long companyId);

    Optional<List<ConstructionStage>> findAllByStatusAndCompanyId(ConstructionStage.StageStatus status, Long companyId);

    @Query("SELECT COUNT(s) FROM ConstructionStage s WHERE s.building.project.id = :projectId AND s.companyId = :companyId")
    Optional<Long> countByProjectIdAndCompanyId(@Param("projectId") Long projectId, @Param("companyId") Long companyId);

    @Query("SELECT COUNT(s) FROM ConstructionStage s WHERE s.status = 'COMPLETED' AND s.building.project.id = :projectId AND s.companyId = :companyId")
    Optional<Long> countCompletedByProjectIdAndCompanyId(@Param("projectId") Long projectId, @Param("companyId") Long companyId);

    @Query("SELECT new map(s.status as status, COUNT(s) as count) FROM ConstructionStage s " +
            "WHERE s.building.project.id = :projectId AND s.companyId = :companyId GROUP BY s.status")
    Optional<List<Map<String, Object>>> getStageAnalytics(@Param("projectId") Long projectId, @Param("companyId") Long companyId);

    @Query("SELECT new map(w.id as workerId, COUNT(wa) as attendance) FROM WorkerAttendance wa " +
            "JOIN wa.worker w WHERE wa.companyId = :companyId GROUP BY w.id")
    Optional<Map<String, Object>> getWorkerUtilization(@Param("companyId") Long companyId);

    @Query("SELECT new map(wa.status as status, COUNT(wa) as count) " +
            "FROM WorkerAttendance wa WHERE wa.companyId = :companyId GROUP BY wa.status")
    Optional<List<Map<String, Object>>> getAttendanceAnalytics(@Param("companyId") Long companyId);

    @Query(value = """
            SELECT 
            s.id as stage_id,
            s.name as stage_name,
            COUNT(wa.id) as total_attendance,
            SUM(CASE WHEN wa.status = 'PRESENT' THEN 1 ELSE 0 END) as present_days,
            COALESCE(SUM(rmu.quantity), 0) as material_used,
            CASE WHEN s.end_date IS NOT NULL AND s.start_date IS NOT NULL 
            THEN DATEDIFF(s.end_date, s.start_date) ELSE 0 END as duration
            FROM const_stages s
            LEFT JOIN worker_attendances wa ON wa.stage_id = s.id
            LEFT JOIN raw_material_usage rmu ON rmu.stage_id = s.id
            WHERE s.company_id = :companyId
            GROUP BY s.id, s.name, s.start_date, s.end_date
            """, nativeQuery = true)
    Optional<Map<String, Object>> getProductivityMetrics(@Param("companyId") Long companyId);

    @Query(value = """
            SELECT
            s.id as stage_id,
            s.name as stage_name,
            COUNT(DISTINCT w.id) as total_workers,
            COUNT(DISTINCT rm.id) as total_materials,
            SUM(rmu.quantity) as material_used,
            COALESCE(SUM(CASE WHEN wa.status = 'PRESENT' THEN 1 ELSE 0 END), 0) as attendance_days
            FROM const_stages s
            LEFT JOIN worker_attendances wa ON wa.stage_id = s.id
            LEFT JOIN workers w ON wa.worker_id = w.id
            LEFT JOIN raw_material_usage rmu ON rmu.stage_id = s.id
            LEFT JOIN raw_materials rm ON rmu.raw_material_id = rm.id
            WHERE s.company_id = :companyId AND s.building_id IN
            (SELECT b.id FROM const_buildings b WHERE b.project_id = :projectId)
            GROUP BY s.id, s.name
            """, nativeQuery = true)
    Optional<Map<String, Object>> getResourceUtilization(@Param("projectId") Long projectId, @Param("companyId") Long companyId);

    @Query(value = """
            SELECT
            p.id as project_id,
            p.name as project_name,
            b.name as building_name,
            s.name as stage_name,
            s.status as stage_status,
            s.start_date,
            s.end_date
            FROM acc_bookings bk
            JOIN const_units u ON bk.unit_id = u.id
            JOIN const_floors f ON u.floor_id = f.id
            JOIN const_buildings b ON f.building_id = b.id
            JOIN const_projects p ON b.project_id = p.id
            LEFT JOIN const_stages s ON s.building_id = b.id
            WHERE bk.customer_id = :customerId
            ORDER BY s.start_date DESC
            """, nativeQuery = true)
    Optional<List<Map<String, Object>>> getCustomerProjectUpdates(@Param("customerId") Long customerId);

    @Query("SELECT s FROM ConstructionStage s " +
            "WHERE s.building.project.id = :projectId " +
            "AND s.companyId = :companyId")
    Optional<List<ConstructionStage>> findByProjectIdAndCompanyId(
            @Param("projectId") Long projectId,
            @Param("companyId") Long companyId
    );
}
