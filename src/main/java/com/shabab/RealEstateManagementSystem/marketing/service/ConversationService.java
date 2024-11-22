package com.shabab.RealEstateManagementSystem.marketing.service;

import com.shabab.RealEstateManagementSystem.marketing.model.Conversation;
import com.shabab.RealEstateManagementSystem.marketing.repository.ConversationRepository;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import com.shabab.RealEstateManagementSystem.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab-1281539
 * Created on: 19/11/2024
 */

@Service
public class ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    public ApiResponse getById(Long id, Long companyId) {
        ApiResponse response = new ApiResponse();
        try {
            Conversation conversation = conversationRepository.findByIdAndCompanyId(id, companyId).orElse(null);
            if (conversation == null) {
                return response.error("Conversation not found");
            }
            response.setData("conversation", conversation);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved conversation");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getAll(Long companyId) {
        ApiResponse response = new ApiResponse();
        try {
            List<Conversation> conversations = conversationRepository.findAllByCompanyId(companyId).orElse(new ArrayList<>());
            response.setData("conversations", conversations);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved conversations");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getByDateRange(Date startDate, Date endDate, Long companyId) {
        ApiResponse response = new ApiResponse();
        try {
            List<Conversation> conversations = conversationRepository.findAllByDateBetweenAndCompanyId(startDate, endDate, companyId).orElse(new ArrayList<>());
            response.setData("conversations", conversations);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved conversations within the date range");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getByCustomerId(Long customerId, Long companyId) {
        ApiResponse response = new ApiResponse();
        try {
            List<Conversation> conversations = conversationRepository.findAllByCustomerIdAndCompanyId(customerId, companyId).orElse(new ArrayList<>());
            response.setData("conversations", conversations);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved conversations for the customer");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getByEmployeeId(Long employeeId, Long companyId) {
        ApiResponse response = new ApiResponse();
        try {
            List<Conversation> conversations = conversationRepository.findAllByEmployeeIdAndCompanyId(employeeId, companyId).orElse(new ArrayList<>());
            response.setData("conversations", conversations);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved conversations for the employee");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getByLeadId(Long leadId, Long companyId) {
        ApiResponse response = new ApiResponse();
        try {
            List<Conversation> conversations = conversationRepository.findAllByLeadIdAndCompanyId(leadId, companyId).orElse(new ArrayList<>());
            response.setData("conversations", conversations);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved conversations for the lead");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse save(Conversation conversation) {
        ApiResponse response = new ApiResponse();
        try {
            conversation.setEmployee(AuthUtil.getCurrentUser());
            conversationRepository.save(conversation);
            response.setData("conversation", conversation);
            response.setSuccessful(true);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse update(Conversation conversation, Long companyId) {
        ApiResponse response = new ApiResponse();
        try {
            // Fetch conversation by id and companyId
            Conversation dbConversation = conversationRepository.findByIdAndCompanyId(conversation.getId(), companyId).orElse(null);
            if (dbConversation == null) {
                return response.error("Conversation not found");
            }
            conversation.setEmployee(AuthUtil.getCurrentUser());
            conversationRepository.save(conversation);
            response.setData("conversation", conversation);
            response.setSuccessful(true);
            response.success("Updated Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse deleteById(Long id, Long companyId) {
        ApiResponse response = new ApiResponse();
        try {
            // Fetch conversation by id and companyId
            Conversation dbConversation = conversationRepository.findByIdAndCompanyId(id, companyId).orElse(null);
            if (dbConversation == null) {
                return response.error("Conversation not found");
            }
            conversationRepository.delete(dbConversation);
            response.setSuccessful(true);
            response.success("Deleted Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }
}


