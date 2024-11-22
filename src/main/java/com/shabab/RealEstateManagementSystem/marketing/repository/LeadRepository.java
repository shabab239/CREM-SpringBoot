package com.shabab.RealEstateManagementSystem.marketing.repository;

import com.shabab.RealEstateManagementSystem.marketing.model.Conversation;
import com.shabab.RealEstateManagementSystem.marketing.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
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

}
