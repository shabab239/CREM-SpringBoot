package com.shabab.RealEstateManagementSystem.marketing.repository;

import com.shabab.RealEstateManagementSystem.marketing.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab-1281539
 * Created on: 19/11/2024
 */

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    Optional<Campaign> findByIdAndCompanyId(Long id, Long companyId);

    Optional<List<Campaign>> findAllByCompanyId(Long companyId);

    Optional<List<Campaign>> findAllByStatusAndCompanyId(Campaign.CampaignStatus status, Long companyId);

    Optional<List<Campaign>> findAllByStartDateBetweenAndCompanyId(Date startDate, Date endDate, Long companyId);

    Optional<List<Campaign>> findAllByEndDateBetweenAndCompanyId(Date startDate, Date endDate, Long companyId);

    Optional<List<Campaign>> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqualAndCompanyId(
            Date endDate, Date startDate, Long companyId);

    Optional<List<Campaign>> findAllByNameContainingAndStartDateBetweenAndCompanyId(
            String name, Date startDate, Date endDate, Long companyId);
}
