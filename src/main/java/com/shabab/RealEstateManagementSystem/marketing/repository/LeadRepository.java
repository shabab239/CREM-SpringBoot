package com.shabab.RealEstateManagementSystem.marketing.repository;

import com.shabab.RealEstateManagementSystem.marketing.model.Conversation;
import com.shabab.RealEstateManagementSystem.marketing.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab-1281539
 * Created on: 19/11/2024
 */

public interface LeadRepository extends JpaRepository<Lead, Long> {

    Optional<Lead> findByIdAndCompanyId(Long id, Long companyId);

    Optional<List<Lead>> findAllByCompanyId(Long companyId);

    Optional<List<Lead>> findAllByStatusAndCompanyId(Lead.LeadStatus status, Long companyId);

    Optional<List<Lead>> findAllByCampaignIdAndCompanyId(Long campaignId, Long companyId);

    @Query(value = """
       SELECT COUNT(l.id) 
       FROM leads l 
       WHERE l.company_id = :companyId
       """, nativeQuery = true)
    Optional<Long> countByCompanyId(@Param("companyId") Long companyId);

    @Query(value = """
       SELECT COUNT(l.id) 
       FROM leads l 
       WHERE l.status IN ('NEW', 'CONTACTED', 'INTERESTED') 
       AND l.company_id = :companyId
       """, nativeQuery = true)
    Optional<Long> countActiveByCompanyId(@Param("companyId") Long companyId);

    @Query(value = """
       SELECT COUNT(l.id) 
       FROM leads l 
       WHERE l.status = 'CONVERTED' 
       AND l.company_id = :companyId
       """, nativeQuery = true)
    Optional<Long> countConvertedByCompanyId(@Param("companyId") Long companyId);

    @Query(value = """
       SELECT l.status, COUNT(l.id) as count 
       FROM leads l 
       WHERE l.company_id = :companyId 
       GROUP BY l.status
       """, nativeQuery = true)
    Optional<Map<String, Object>> getConversionAnalytics(@Param("companyId") Long companyId);

}
