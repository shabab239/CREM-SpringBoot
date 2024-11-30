package com.shabab.RealEstateManagementSystem.marketing.repository;

import com.shabab.RealEstateManagementSystem.marketing.model.Campaign;
import com.shabab.RealEstateManagementSystem.marketing.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab-1281539
 * Created on: 19/11/2024
 */

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    Optional<List<Conversation>> findAllByCompanyId(Long companyId);

    Optional<List<Conversation>> findAllByLead_IdAndCompanyId(Long leadId, Long companyId);

    Optional<Conversation> findByIdAndCompanyId(Long id, Long companyId);

    Optional<List<Conversation>> findAllByDateBetweenAndCompanyId(Date startDate, Date endDate, Long companyId);

    Optional<List<Conversation>> findAllByCustomerIdAndCompanyId(Long customerId, Long companyId);

    Optional<List<Conversation>> findAllByEmployeeIdAndCompanyId(Long employeeId, Long companyId);

    Optional<List<Conversation>> findAllByLeadIdAndCompanyId(Long leadId, Long companyId);
}
