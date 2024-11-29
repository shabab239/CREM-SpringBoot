package com.shabab.RealEstateManagementSystem.core.repository;

import com.shabab.RealEstateManagementSystem.core.model.construction.Project;
import org.springframework.data.jpa.repository.JpaRepository;
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

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<List<Project>> findAllByCompanyId(Long companyId);

    Optional<Project> findByIdAndCompanyId(Long id, Long companyId);

    Optional<Project> findByNameAndCompanyId(String name, Long companyId);

    @Query("SELECT COUNT(p) FROM Project p WHERE p.companyId = :companyId")
    Optional<Long> countByCompanyId(@Param("companyId") Long companyId);

    @Query(value = """
       SELECT 
       (SUM(CASE WHEN s.status = 'COMPLETED' THEN 1 ELSE 0 END) * 100.0 / COUNT(s.id)) 
       FROM const_stages s 
       JOIN const_buildings b ON s.building_id = b.id 
       WHERE b.project_id = :projectId AND b.company_id = :companyId
       """, nativeQuery = true)
    Optional<Double> getProjectProgress(@Param("projectId") Long projectId, @Param("companyId") Long companyId);

    @Query(value = """
       SELECT s.status, COUNT(s.id) as count 
       FROM const_stages s 
       JOIN const_buildings b ON s.building_id = b.id 
       WHERE b.project_id = :projectId AND b.company_id = :companyId 
       GROUP BY s.status
       """, nativeQuery = true)
    Optional<Map<String, Object>> getTimelineProgress(@Param("projectId") Long projectId, @Param("companyId") Long companyId);



}
