package com.shabab.RealEstateManagementSystem.marketing.service;

import com.shabab.RealEstateManagementSystem.marketing.model.Conversation;
import com.shabab.RealEstateManagementSystem.marketing.repository.ConversationRepository;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Conversation conversation = conversationRepository.findById(id).orElse(null);
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

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Conversation> conversations = conversationRepository.findAll();
            response.setData("conversations", conversations);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved conversations");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse save(Conversation conversation) {
        ApiResponse response = new ApiResponse();
        try {
            conversationRepository.save(conversation);
            response.setData("conversation", conversation);
            response.setSuccessful(true);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse update(Conversation conversation) {
        ApiResponse response = new ApiResponse();
        try {
            Conversation dbConversation = conversationRepository.findById(conversation.getId()).orElse(null);
            if (dbConversation == null) {
                return response.error("Conversation not found");
            }
            conversationRepository.save(conversation);
            response.setData("conversation", conversation);
            response.setSuccessful(true);
            response.success("Updated Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Conversation dbConversation = conversationRepository.findById(id).orElse(null);
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


