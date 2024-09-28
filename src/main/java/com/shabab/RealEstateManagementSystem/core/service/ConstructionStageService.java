package com.shabab.RealEstateManagementSystem.core.service;

import com.shabab.RealEstateManagementSystem.core.model.ConstructionStage;
import com.shabab.RealEstateManagementSystem.core.repository.ConstructionStageRepository;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import com.shabab.RealEstateManagementSystem.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 28/09/2024
 */

@Service
public class ConstructionStageService {

    @Autowired
    private ConstructionStageRepository constructionStageRepository;

    public ApiResponse findById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            ConstructionStage dbConstructionStage = constructionStageRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbConstructionStage == null) {
                return response.error("Construction Stage not found");
            }
            response.setData("constructionStage", dbConstructionStage);
            response.setMessage("Successfully retrieved construction stage");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse findAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<ConstructionStage> constructionStages = constructionStageRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            if (constructionStages.isEmpty()) {
                return response.error("No construction stage found");
            }
            response.setData("constructionStages", constructionStages);
            response.setMessage("Successfully retrieved all construction stages");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse save(ConstructionStage constructionStage) {
        ApiResponse response = new ApiResponse();
        try {
            constructionStage.setCompanyId(AuthUtil.getCurrentCompanyId());
            constructionStageRepository.save(constructionStage);
            response.setData("constructionStage", constructionStage);
            response.setMessage("Successfully saved construction stage");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse update(ConstructionStage constructionStage) {
        ApiResponse response = new ApiResponse();
        try {
            ConstructionStage dbConstructionStage = constructionStageRepository.findByIdAndCompanyId(
                    constructionStage.getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbConstructionStage == null) {
                return response.error("Construction Stage not found");
            }
            constructionStage.setCompanyId(AuthUtil.getCurrentCompanyId());
            constructionStageRepository.save(constructionStage);
            response.setData("constructionStage", constructionStage);
            response.setMessage("Successfully updated construction stage");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            ConstructionStage constructionStage = constructionStageRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (constructionStage == null) {
                return response.error("Construction Stage not found");
            }
            constructionStageRepository.deleteById(id);
            response.setMessage("Successfully deleted construction stage");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

}
