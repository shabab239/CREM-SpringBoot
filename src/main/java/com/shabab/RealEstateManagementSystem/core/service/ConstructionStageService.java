package com.shabab.RealEstateManagementSystem.core.service;

import com.shabab.RealEstateManagementSystem.core.model.ConstructionStage;
import com.shabab.RealEstateManagementSystem.core.repository.ConstructionStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 28/09/2024
 */

@Service
public class ConstructionStageService {

    @Autowired
    private ConstructionStageRepository constructionStageRepository;

    public Optional<ConstructionStage> findConstructionStageById(Long id) {
        return constructionStageRepository.findById(id);
    }

    public List<ConstructionStage> findAllConstructionStages() {
        return constructionStageRepository.findAll();
    }

    public ConstructionStage saveConstructionStage(ConstructionStage constructionStage) {
        return constructionStageRepository.save(constructionStage);
    }

    public void deleteConstructionStageById(Long id) {
        constructionStageRepository.deleteById(id);
    }

}
