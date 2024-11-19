package com.shabab.RealEstateManagementSystem.marketing.repository;

import com.shabab.RealEstateManagementSystem.marketing.model.Campaign;
import com.shabab.RealEstateManagementSystem.marketing.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab-1281539
 * Created on: 19/11/2024
 */

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

}
