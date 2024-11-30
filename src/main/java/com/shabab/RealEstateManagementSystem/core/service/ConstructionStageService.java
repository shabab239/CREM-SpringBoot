package com.shabab.RealEstateManagementSystem.core.service;

import com.shabab.RealEstateManagementSystem.core.model.construction.ConstructionStage;
import com.shabab.RealEstateManagementSystem.core.model.worker.Worker;
import com.shabab.RealEstateManagementSystem.core.repository.ConstructionStageRepository;
import com.shabab.RealEstateManagementSystem.core.repository.WorkerRepository;
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
    @Autowired
    private WorkerRepository workerRepository;

    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            ConstructionStage constructionStage = constructionStageRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (constructionStage == null) {
                return response.error("Construction Stage not found");
            }
            response.setData("constructionStage", constructionStage);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved construction stage");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<ConstructionStage> constructionStages = constructionStageRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("constructionStages", constructionStages);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved all construction stages");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getAllStagesByStatus(ConstructionStage.StageStatus status) {
        ApiResponse response = new ApiResponse();
        try {
            List<ConstructionStage> constructionStages = constructionStageRepository.findAllByStatusAndCompanyId(
                    status, AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("constructionStages", constructionStages);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved all construction stages");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse save(ConstructionStage constructionStage) {
        ApiResponse response = new ApiResponse();
        try {
            if (constructionStage.getBuilding() != null && constructionStage.getBuilding().getId() == null) {
                constructionStage.setBuilding(null);
            }
            if (constructionStage.getFloor() != null && constructionStage.getFloor().getId() == null) {
                constructionStage.setFloor(null);
            }
            if (constructionStage.getUnit() != null && constructionStage.getUnit().getId() == null) {
                constructionStage.setUnit(null);
            }
            constructionStage.setCompanyId(AuthUtil.getCurrentCompanyId());
            constructionStageRepository.save(constructionStage);
            response.setData("constructionStage", constructionStage);
            response.setSuccessful(true);
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
            response.setSuccessful(true);
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
            response.setSuccessful(true);
            response.setMessage("Successfully deleted construction stage");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getAllStagesByBuildingId(Long buildingId) {
        ApiResponse response = new ApiResponse();
        try {
            List<ConstructionStage> constructionStages = constructionStageRepository.findAllByBuildingIdAndCompanyId(
                    buildingId, AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("constructionStages", constructionStages);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved all construction stages by building");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getAllStagesByFloorId(Long floorId) {
        ApiResponse response = new ApiResponse();
        try {
            List<ConstructionStage> constructionStages = constructionStageRepository.findAllByFloorIdAndCompanyId(
                    floorId, AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("constructionStages", constructionStages);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved all construction stages by floor");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getAllStagesByUnitId(Long unitId) {
        ApiResponse response = new ApiResponse();
        try {
            List<ConstructionStage> constructionStages = constructionStageRepository.findAllByUnitIdAndCompanyId(
                    unitId, AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("constructionStages", constructionStages);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved all construction stages by unit");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getAllWorkersByStageId(Long stageId) {
        ApiResponse response = new ApiResponse();
        try {
            ConstructionStage constructionStage = constructionStageRepository.findByIdAndCompanyId(
                    stageId, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (constructionStage == null) {
                return response.error("Construction Stage not found");
            }
            response.setData("workers", constructionStage.getWorkers());
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved all workers by stage");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse addWorkerToStage(Long stageId, Long workerId) {
    ApiResponse response = new ApiResponse();
    try {
        ConstructionStage constructionStage = constructionStageRepository.findByIdAndCompanyId(
                stageId, AuthUtil.getCurrentCompanyId()
        ).orElse(null);
        if (constructionStage == null) {
            return response.error("Construction Stage not found");
        }
        Worker worker = workerRepository.findByIdAndCompanyId(
                workerId, AuthUtil.getCurrentCompanyId()
        ).orElse(null);
        if (worker == null) {
            return response.error("Worker not found");
        }
        if (constructionStage.getWorkers().contains(worker)) {
            constructionStage.getWorkers().remove(worker);
            response.setMessage("Successfully removed worker from stage");
        } else {
            constructionStage.getWorkers().add(worker);
            response.setMessage("Successfully added worker to stage");
        }
        constructionStageRepository.save(constructionStage);
        response.setSuccessful(true);
    } catch (Exception e) {
        return response.error(e);
    }
    return response;
}

}
